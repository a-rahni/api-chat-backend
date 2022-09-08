package fr.m2i.apichat.service;

import fr.m2i.apichat.exception.CanalNotFoundException;
import fr.m2i.apichat.exception.NotFoundException;
import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.Message;
import fr.m2i.apichat.model.User;
import fr.m2i.apichat.repository.CanalRepository;
import fr.m2i.apichat.repository.MessageRepository;
import fr.m2i.apichat.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CanalService implements ICanalService {
    private final UserRepository userRepo;
    private final CanalRepository canalRepo;
    private final MessageRepository messageRepo;
    @Autowired
    public CanalService(UserRepository userRepo, CanalRepository canalRepository, MessageRepository messageRepo) {
        this.userRepo = userRepo;
        this.canalRepo = canalRepository;
        this.messageRepo = messageRepo;
    }

    @Override
    public void addMessageToCanal(String username, String canalID, String message) {
        log.info("Adding Message:{} to Canal:{} username:{}", username,canalID,message);
        //Find the user
        User user = userRepo.findByUsername(username);
        //Find canal
        Optional<Canal> c= canalRepo.findById(Long.parseLong(canalID));
        Message m=new Message();
        m.setCanal(c.get());
        m.setContent(message);
        m.setCreatedAt(new Date());
        m.setUpdatedAt(new Date());
        m.setUser(user);
        messageRepo.save(m);
    }

    @Override
    public List<Canal> getCanals() {
        return canalRepo.findAll();
    }


    @Override
    public void deleteCanal(Long id) {
        canalRepo.findById(id).orElseThrow(()->new CanalNotFoundException(id));
        canalRepo.deleteById(id);
    }

    @Override
    public List<Canal> findByCanals(String canalName) {
        return (List<Canal>) canalRepo.findByNameContaining(canalName);
    }

    @Override
    public Canal findById(Long id) {
        return canalRepo.findById(id).orElseThrow(()->new CanalNotFoundException(id));
    }


    @Override
    public Canal addCanal(Canal canal) {
        log.info("Saving new Canal to the database...");
        canal.setCreatedAt(new Date());
        canal.setUpdatedAt(new Date());
        return canalRepo.save(canal);
    }

    @Override
    public Canal updateCanal(Canal canal) {
        canal.setUpdatedAt(new Date());
        return canalRepo.save(canal);
    }
}
