package fr.m2i.apichat.service;

import fr.m2i.apichat.dto.MessageMapper;
import fr.m2i.apichat.dto.UserMapper;
import fr.m2i.apichat.exception.AlreadyExistsException;
import fr.m2i.apichat.exception.NotFoundException;
import fr.m2i.apichat.model.Message;
import fr.m2i.apichat.model.User;
import fr.m2i.apichat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService implements  IMessageService{

    private final MessageRepository repo;

    @Autowired
    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Message> findAll() {
        return repo.findAll();
    }

    @Override
    public Message findById(Long id) {
        return repo.findById(id).orElseThrow(()->
        {throw new NotFoundException("the message with id: "+id+" was not found"); }
        );
    }

//    public Page<Message> findMessagesByCanalId(Long canalId, int page, int size){
//        return repo.findByCanal(canalId, PageRequest.of(page,size));
//    }

    public List<Message> findMessagesByCanalId(Long canalId){
        return repo.findByCanal(canalId);
    }

    @Override
    public Message save(Message message) {
        // check que l'id est non fourni, sinon on risque de modifier un existant
        // ou set id to null
        return repo.save(message);
    }

    @Override
    public Message update(Long id, Message messageContent) {
        Message found= findById(id);
        MessageMapper.copy(found,messageContent);
        if(messageContent!=null && messageContent.getUpdatedAt()==null){
            messageContent.setUpdatedAt(new Date());
        }
        return repo.save(found);

    }

    @Override
    public void delete(Long id) {
        Message found= findById(id);
        repo.delete(found);
    }
}
