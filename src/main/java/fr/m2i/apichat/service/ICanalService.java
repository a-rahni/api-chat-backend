package fr.m2i.apichat.service;

import fr.m2i.apichat.model.Canal;

import java.util.List;

public interface ICanalService {
    public List<Canal> findAll();
    public Canal findById(Long id);
    public Canal save(Canal canal);
    public Canal update(Long id, Canal canalContent);
    public void delete(Long id);
}
