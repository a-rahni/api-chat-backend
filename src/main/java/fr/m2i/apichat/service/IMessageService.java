package fr.m2i.apichat.service;

import fr.m2i.apichat.model.Message;

import java.util.List;
public interface IMessageService {
    Message updateMessage(Message message);
    List<Message> findMessageUserCanal(int page, int size,String username,String canal);
    List<Message> findAll();
    Message getMessageById(Long id);
}
