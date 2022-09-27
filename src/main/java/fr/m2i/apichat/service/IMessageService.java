package fr.m2i.apichat.service;

import fr.m2i.apichat.model.Message;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMessageService {
    public List<Message> findAll();
    public Message findById(Long id);
    public Message save(Message message);
    public Message update(Long id, Message messageContent);
    public void delete(Long id);

    public List<Message> findMessagesByCanalId(Long canalId);
}
