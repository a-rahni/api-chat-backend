package fr.m2i.apichat.service;

import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.Message;

import java.util.List;

public interface ICanalService {
    public List<Canal> findAll();
    public Canal findById(Long id);

    public Canal findByName(String name);
    public Canal save(Canal canal);
    public Canal update(Long id, Canal canalContent);
    public void delete(Long id);

    public Message addMessage(Long idCanal, Long idUser, Message message);
    public List<Message> getMessages(Long idCanal);
}
