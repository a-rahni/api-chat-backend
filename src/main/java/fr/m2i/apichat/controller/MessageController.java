package fr.m2i.apichat.controller;

import fr.m2i.apichat.dto.CanalDTO;
import fr.m2i.apichat.dto.CanalMapper;
import fr.m2i.apichat.dto.MessageDTO;
import fr.m2i.apichat.dto.MessageMapper;
import fr.m2i.apichat.exception.NotFoundException;
import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.Message;
import fr.m2i.apichat.response.ErrorResponseEntity;
import fr.m2i.apichat.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/messages")
public class MessageController {
    private IMessageService messageService;

    @Autowired
    public MessageController(IMessageService messageService){
        this.messageService= messageService;
    }

    @PutMapping(value="/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces=MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "update a message", nickname = "update a message by id", response = MessageDTO.class)
    public ResponseEntity<Object> updateMessage(@PathVariable("id") String id, @RequestBody MessageDTO messageDto){

        try {
            Long idMessage = Long.parseLong(id);
            Message messageContent = MessageMapper.buildMessage(messageDto);
            Message updatedMessage = messageService.update(idMessage, messageContent);
            MessageDTO updatedDTO = MessageMapper.buildMessageDTO(updatedMessage);

            return ResponseEntity.status(HttpStatus.OK).body(updatedDTO);

        } catch(NumberFormatException ne){
            return ErrorResponseEntity.build("The parameter 'id' is not valid", 400, "/v1/messages/" + id, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Message was not found", 404, "/v1/messages/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ErrorResponseEntity.build("An error occured", 500, "/v1/messages/"+ id, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/{id}")
    //@ApiOperation(value = "delete a message", nickname = "Delete a message by id", code = 204)
    public ResponseEntity<Object> deleteCanalById(@PathVariable("id") String id){
        try{
            Long idCanal = Long.parseLong(id);
            messageService.delete(idCanal);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(NumberFormatException ne){
            return ErrorResponseEntity.build("The parameter 'id' is not valid", 400, "/v1/messages/" + id, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException nfe) {
            return ErrorResponseEntity.build("Message was not found", 404, "/v1/messages/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println("****"+e.getMessage());
            return ErrorResponseEntity.build("An error occured", 500, "/v1/messages/" + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
