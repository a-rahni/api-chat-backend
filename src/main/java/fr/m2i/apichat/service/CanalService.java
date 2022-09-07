package fr.m2i.apichat.service;

import fr.m2i.apichat.dto.CanalMapper;
import fr.m2i.apichat.dto.MessageDTO;
import fr.m2i.apichat.dto.MessageMapper;
import fr.m2i.apichat.exception.AlreadyExistsException;
import fr.m2i.apichat.exception.NotFoundException;
import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.Message;
import fr.m2i.apichat.model.User;
import fr.m2i.apichat.repository.CanalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CanalService implements  ICanalService{
    private final CanalRepository repo;
    private final IUserService userService;
    private final IMessageService messageService;

    @Autowired
    public CanalService(CanalRepository repo, IUserService userService, IMessageService messageService){
        this.repo = repo;
        this.userService=userService;
        this.messageService= messageService;
    }

    @Override
    public List<Canal> findAll() {
        return repo.findAll();
    }

    @Override
    public Canal findById(Long id) {
       return repo.findById(id).orElseThrow(()->
       {throw new NotFoundException("Canal with id: "+ id + " was not found"); });
    }

    @Override
    public Canal findByName(String name) {
        Canal canal= repo.findByName(name);
        if(canal == null) {
            throw new NotFoundException("Canal with name: "+ name + " was not found");
        }
        return canal;
    }

    @Override
    public Canal save(Canal canal) {
        // check que l'id est non fourni, sinon on risque de modifier un existant
        // ou set id to null

        Canal canalWithName = null;
        if (canal!= null){
            canalWithName = repo.findByName(canal.getName());
        }

        if(canalWithName != null){
            throw new AlreadyExistsException(" Canal with name : "+ canalWithName.getName() + "already exists");
        }

        if(canal != null){
            if(canal.getCreatedAt() == null){
                canal.setCreatedAt(new Date());
            }
            if(canal.getUpdatedAt() == null){
                canal.setUpdatedAt(new Date());
            }
        }
        return repo.save(canal);
    }

    @Override
    public Canal update(Long id, Canal canalContent) {
        Canal canalToUpdate = findById(id);
        CanalMapper.copy(canalToUpdate, canalContent);
        if(canalToUpdate != null && canalToUpdate.getUpdatedAt()== null){
            canalToUpdate.setUpdatedAt(new Date());
        }
        return repo.save(canalToUpdate);
    }

    @Override
    public void delete(Long id) {
        Canal canalToDelete= findById(id);
        repo.delete(canalToDelete);
    }

    public Message addMessage(Long idCanal, Long idUser, Message message){
        Canal canal= findById(idCanal);
        User user = userService.findById(idUser);

        if(message != null){
            message.setCanal(canal);
            message.setUser(user);

            if(message.getCreatedAt() == null){
                message.setCreatedAt(new Date());
            }
            if(message.getUpdatedAt() == null){
                message.setUpdatedAt(new Date());
            }
            return messageService.save(message);
        }
        return null;
    }

    public List<Message> getMessages(Long idCanal) {
        Canal canal = findById(idCanal);
        //List<Message> messages = messageService.findMessagesByCanalId(canal.getId());
        return canal.getMessages();
    }

//        List<MessageDTO> listm = messagesPage.stream().map(m-> MessageMapper.buildMessageDTO(m)).collect(Collectors.toList());
//        return ResponseEntity.status(HttpStatus.OK).
//                body(new PageImpl<MessageDTO>(listm,messagesPage.getPageable(),messagesPage.getTotalElements()));
}
