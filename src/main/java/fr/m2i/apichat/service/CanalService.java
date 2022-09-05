package fr.m2i.apichat.service;

import fr.m2i.apichat.dto.CanalMapper;
import fr.m2i.apichat.exception.AlreadyExistsException;
import fr.m2i.apichat.exception.NotFoundException;
import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.User;
import fr.m2i.apichat.repository.CanalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanalService implements  ICanalService{
    private final CanalRepository repo;

    @Autowired
    public CanalService(CanalRepository repo){
        this.repo = repo;
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
        return repo.save(canal);
    }

    @Override
    public Canal update(Long id, Canal canalContent) {
        Canal canalToUpdate = findById(id);
        CanalMapper.copy(canalToUpdate, canalContent);
        return repo.save(canalToUpdate);
    }

    @Override
    public void delete(Long id) {
        Canal canalToDelete= findById(id);
        repo.delete(canalToDelete);
    }
}
