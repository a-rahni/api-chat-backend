package fr.m2i.apichat.controller;

import fr.m2i.apichat.dto.CanalDTO;
import fr.m2i.apichat.dto.CanalMapper;
import fr.m2i.apichat.dto.MessageDTO;
import fr.m2i.apichat.dto.MessageMapper;
import fr.m2i.apichat.exception.AlreadyExistsException;
import fr.m2i.apichat.exception.NotFoundException;
import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.Message;
import fr.m2i.apichat.response.ErrorResponseEntity;
import fr.m2i.apichat.service.ICanalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/canaux")
public class CanalController {
    private ICanalService canalService;

    @Autowired
    public CanalController(ICanalService canalService) {
        this.canalService = canalService;
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Returns the list of all channel", nickname = "Get all channel", response = CanalDTO.class)
    public ResponseEntity<Object> getAllCanals(){
        List<Canal> canaux = canalService.findAll();
        List<CanalDTO> dtos = CanalMapper.buidListCanalDTO(canaux);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Return a canal", nickname = "Get a canal by id", response = CanalDTO.class)
    public ResponseEntity<Object> getCanalById(@PathVariable("id") String id){
        try{
            Long idCanal = Long.parseLong(id);
            Canal canal= canalService.findById(idCanal);
            return ResponseEntity.status(HttpStatus.OK).body(CanalMapper.buildCanalDTO(canal));
        }catch(NumberFormatException ne){
            return ErrorResponseEntity.build("The parameter 'id' is not valid", 400, "/v1/canaux/" + id, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Canal was not found", 404, "/v1/canaux/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/v1/canaux/" + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Return a canal", nickname = "Get a canal by name", response = CanalDTO.class)
    public ResponseEntity<Object> getCanalByName(@RequestParam(name="name",defaultValue = "") String name ){
        try{
            Canal canal= canalService.findByName(name);
            return ResponseEntity.status(HttpStatus.OK).body(CanalMapper.buildCanalDTO(canal));
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Canal was not found", 404, "/v1/canaux/search?name" + name, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/v1/canaux/search?name=" + name, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Create a canal", nickname = "Create a canal", response = CanalDTO.class)
    public ResponseEntity<Object> createCanal(@RequestBody CanalDTO canalDto) {

        try {
            Canal toCreate = CanalMapper.buildCanal(canalDto);
            Canal created = canalService.save(toCreate);
            CanalDTO createdDTO = CanalMapper.buildCanalDTO(created);
            return ResponseEntity.status(HttpStatus.OK).body(createdDTO);
        }catch (AlreadyExistsException ae){
            return ErrorResponseEntity.build("the canal name is already used", 400, "/v1/canaux", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println("******"+ e.getMessage());
            return ErrorResponseEntity.build("An error occured", 500, "/v1/canaux", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "update a canal", nickname = "Update a canal by id", response = CanalDTO.class)
    public ResponseEntity<Object> updateCanal(@PathVariable("id") String id, @RequestBody CanalDTO canalDto) {

        try {
            Long idCanal = Long.parseLong(id);
            Canal orderContent = CanalMapper.buildCanal(canalDto);
            Canal updatedCanal = canalService.update(idCanal, orderContent);
            CanalDTO updatedDTO = CanalMapper.buildCanalDTO(updatedCanal);

            return ResponseEntity.status(HttpStatus.OK).body(updatedDTO);

        } catch(NumberFormatException ne){
            return ErrorResponseEntity.build("The parameter 'id' is not valid", 400, "/v1/canaux/" + id, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Canal was not found", 404, "/v1/canaux/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/v1/canaux/"+ id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value="/{idcanal}/{iduser}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "add a message", nickname = "Create a message by channel and user id", response = MessageDTO.class)
    public ResponseEntity<Object> addMessage(@PathVariable("idcanal") String idcanal,
                                             @PathVariable("iduser") String iduser,
                                             @RequestBody MessageDTO messageDto) {

        try {
            Long idCanal = Long.parseLong(idcanal);
            Long idUser = Long.parseLong(iduser);
            Message message = MessageMapper.buildMessage(messageDto);
            Message createdMessage = canalService.addMessage(idCanal, idUser, message);
            MessageDTO createdDTO = MessageMapper.buildMessageDTO(createdMessage);

            return ResponseEntity.status(HttpStatus.OK).body(createdDTO);

        } catch(NumberFormatException ne){
            return ErrorResponseEntity.build("The parameters 'id canal' or 'id user' is not valid", 400, "/v1/canaux/idcanal/iduser", HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Canal or user was not found", 404, "/v1/canaux/idcanal/iduser" , HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/v1/canaux/idcanal/iduser", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
