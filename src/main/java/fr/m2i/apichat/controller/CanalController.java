package fr.m2i.apichat.controller;
import fr.m2i.apichat.dtos.CanalDto;
import fr.m2i.apichat.exception.ApiRequestException;
import fr.m2i.apichat.mappers.CanalMapper;
import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.service.ICanalService;
import fr.m2i.apichat.service.IMessageService;
import fr.m2i.apichat.service.IUserService;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/v1/canaux")
public class CanalController {

    private final IMessageService messageService;
    private final IUserService userService;

    private final ICanalService canalService;


    private final CanalMapper mapper;
    @Autowired
    public CanalController(IMessageService messageService, IUserService userService, ICanalService canalService, CanalMapper mapper) {
        this.messageService = messageService;
        this.userService = userService;
        this.canalService = canalService;
        this.mapper = mapper;
    }

    // fournit des informations relatives à cette opération (verbe + chemin)
    @GetMapping
    @ApiOperation(value = "Renvoyer la listes des canaux ou un canal précis  ", response = CanalDto.class)
    public ResponseEntity<List<CanalDto>> findAllCanaux(@RequestParam(required=false) String name ){
        log.info("find all canaux");
        List<Canal> canaux = new ArrayList<Canal>();
        if (name == null)
            canalService.getCanals().forEach(canaux::add);
        else
            canalService.findByCanals(name).forEach(canaux::add);

        if (canaux.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CanalDto> canauxDto=canaux.stream()
                .map(mapper::canalToCanalDto)
                .collect(Collectors.toList());
        return new  ResponseEntity<>(canauxDto,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retourner un canal by ID", response = CanalDto.class)
    public ResponseEntity<Object> getCanalById(@PathVariable("id") String id){
            Long idCanal = Long.parseLong(id);
            Canal canal= canalService.findById(idCanal);
            return new ResponseEntity<>(mapper.canalToCanalDto(canal),HttpStatus.OK);

    }



    @PostMapping
    @ApiOperation(value = "Créer un canal  ", nickname = "Create canal ", response = CanalDto.class)
    public ResponseEntity<CanalDto> create(@RequestBody @Valid @NonNull CanalDto canalDto) throws ApiRequestException {
        Canal toCreate=canalService.addCanal(mapper.canalDtoToCanal(canalDto));
        return new ResponseEntity<CanalDto>(mapper.canalToCanalDto(toCreate),HttpStatus.CREATED);
    }


    @PostMapping("/{canalId}/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity sendMessageToCanal(@RequestBody MessageForm form,@PathVariable("canalId") String canalID )  {
        //String username, String canal, String message
        canalService.addMessageToCanal(form.getUsername(),canalID,form.getText());
        return ResponseEntity.status(HttpStatus.CREATED).body("Message envoyer");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CanalDto> update(@PathVariable("id") Long id, @RequestBody @Valid CanalDto canalDto)  {
        if(canalDto.getId()== null)
            throw new ApiRequestException("Problème Id ");
        Canal canal = canalService.findById(canalDto.getId());
        Canal _canal=mapper.updateCanalFromCanalDto(canalDto,canal);
        canalService.updateCanal(_canal);
        return  new ResponseEntity<>(mapper.canalToCanalDto(_canal),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        canalService.deleteCanal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

