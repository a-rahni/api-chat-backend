package fr.m2i.apichat.controller;

import fr.m2i.apichat.dto.CanalDTO;
import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.service.ICanalService;
import fr.m2i.apichat.service.IMessageService;
import fr.m2i.apichat.service.IUserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "http://localhost:8080") //Problème du front end
@RestController
@RequestMapping("/v1/canals")
public class CanalController {

    private final IMessageService messageService;
    private final IUserService userService;
    private final ICanalService canalService;
    private final ModelMapper modelMapper;
    @Autowired
    public CanalController(IMessageService messageService, IUserService userService, ICanalService canalService, ModelMapper modelMapper) {
        this.messageService = messageService;
        this.userService = userService;
        this.canalService = canalService;
        this.modelMapper = modelMapper;
    }
    @GetMapping

    public ResponseEntity<List<CanalDTO>> findAllCanaux(@RequestParam(required=false) String name ){
        log.info("find all canals");
        List<Canal> canaux = new ArrayList<Canal>();
        if (name == null)
            canalService.getCanals().forEach(canaux::add);
        else
            canalService.findByCanals(name).forEach(canaux::add);

        if (canaux.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CanalDTO> canauxDTO=canaux.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new  ResponseEntity<>(canauxDTO,HttpStatus.OK);
    }



    @PostMapping("/add")
    public ResponseEntity<CanalDTO> create(@RequestBody CanalDTO canalDto) throws ParseException {
        Canal canal=convertToEntity(canalDto);
        Canal canalCreated = canalService.addCanal(canal);
        return new ResponseEntity<CanalDTO>(convertToDto(canalCreated),HttpStatus.CREATED);
    }


    @PostMapping("/{canalId}/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity sendMessageToCanal(@RequestBody MessageForm form,@PathVariable("canalId") String canalID )  {
        //String username, String canal, String message
        canalService.addMessageToCanal(form.getUsername(),canalID,form.getText());
        return ResponseEntity.status(HttpStatus.CREATED).body("Message envoyer");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Canal> update(@PathVariable("id") Long id, @RequestBody Canal canal) throws ParseException {
        Canal _canal = canalService.findById(id);
        return  new ResponseEntity<>(_canal,HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        canalService.deleteCanal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private CanalDTO  convertToDto(Canal canal) {
        CanalDTO canalDTO=modelMapper.map(canal,CanalDTO.class);
        return canalDTO;
    }
    private Canal convertToEntity(CanalDTO canalDto) throws ParseException {
        Canal canal = modelMapper.map(canalDto, Canal.class);
        return canal;
    }

}
@Data
class MessageForm{
    private String username;;
    private String text;
}

