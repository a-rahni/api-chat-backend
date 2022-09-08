package fr.m2i.apichat.service;

import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.Message;

import java.util.List;

public interface ICanalService {
    void addMessageToCanal(String username,String canal,String message);
    List<Canal> getCanals();
    Canal addCanal(Canal canal);
    Canal updateCanal(Canal canal);
    void deleteCanal(Long id);
    List<Canal> findByCanals(String canalName);
    Canal findById(Long id);

}
