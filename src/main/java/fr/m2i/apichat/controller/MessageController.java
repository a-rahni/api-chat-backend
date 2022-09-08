package fr.m2i.apichat.controller;


import fr.m2i.apichat.model.Message;
import fr.m2i.apichat.service.ICanalService;
import fr.m2i.apichat.service.IMessageService;
import fr.m2i.apichat.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/v1/messages")
public class MessageController {
    private final IMessageService messageService;
    private final IUserService userService;
    private final ICanalService canalService;
    @Autowired
    public MessageController(IMessageService messageService, IUserService userService, ICanalService canalService) {
        this.messageService = messageService;
        this.userService = userService;
        this.canalService = canalService;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Object> Messages(){
        log.info("find all message");
        List<Message> messages = messageService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getMessages(
            @PathVariable(value = "page", required = false) Integer page,
            @PathVariable(value = "size",required = false) Integer size,
            @PathVariable(value = "sortDir",required = false) String sortDir,
            @PathVariable(value = "sort",required = false) String sort,
            @RequestParam("username") String username,
            @RequestParam("canal") String canal){
        log.info(" canal={} username={} ",canal,username );
        List<Message> messages = messageService.findMessageUserCanal(1, 10,username,canal);

        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public Message getPost(@PathVariable("id") Long id) {
        return messageService.getMessageById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@PathVariable("id") Long id, @RequestBody Message message) throws ParseException {
        if(!Objects.equals(id, message.getId())){
            throw new IllegalArgumentException("Id non compatible");
        }

        messageService.updateMessage(message);
    }


}
