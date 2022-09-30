package fr.m2i.apichat.service;

import fr.m2i.apichat.model.Message;
import fr.m2i.apichat.repository.CanalRepository;
import fr.m2i.apichat.repository.MessageRepository;
import fr.m2i.apichat.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
@Slf4j
public class MessageService implements IMessageService{
    private final MessageRepository messageRepo;
    private final UserRepository userRepo;
    private final CanalRepository canalRepository;
    private final IUserService userService;
    @Autowired
    public MessageService(MessageRepository messageRepo, UserRepository userRepo, CanalRepository canalRepository, IUserService userService) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
        this.canalRepository = canalRepository;
        this.userService = userService;
    }
    @Override
    public List<Message> findMessageUserCanal(String username,String canal) {
        log.info("username={} canal={}",username,canal);
        List<Message> messages= messageRepo.findAllMessage(username,canal);
        return messages;
    }

    @Override
    public List<Message> findAll() {
        return  messageRepo.findAll();
    }


    @Override
    public Message updateMessage(Message message) {

        return messageRepo.save(message);
    }

    @Override
    public Message getMessageById(Long id) {
        return  messageRepo.getMessageById(id);
    }


}
