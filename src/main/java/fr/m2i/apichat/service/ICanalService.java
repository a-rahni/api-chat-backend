package fr.m2i.apichat.service;

import fr.m2i.apichat.exception.ApiRequestException;
import fr.m2i.apichat.model.Canal;

import java.util.List;

public interface ICanalService {
    void addMessageToCanal(String username,String canal,String message);
    List<Canal> getCanals();
    Canal addCanal(Canal canal) throws ApiRequestException;
    Canal updateCanal(Canal canal);
    void deleteCanal(Long id);
    List<Canal> findByCanals(String canalName);
    Canal findById(Long id);

}
