package fr.m2i.apichat.service;

import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.repository.CanalRepository;
import fr.m2i.apichat.repository.MessageRepository;
import fr.m2i.apichat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Stream;

@Service
@Transactional
public class ServiceInitDB {
    private final CanalRepository canalRepo;
    private final UserRepository userRepo;
    private final MessageRepository messageRepo;
    @Autowired
    public ServiceInitDB(CanalRepository canalRepo, UserRepository userRepo, MessageRepository messageRepo) {
        this.canalRepo = canalRepo;
        this.userRepo = userRepo;
        this.messageRepo = messageRepo;
    }
    public void initCanaux(){
/*
        Stream.of(new Canal(null,"#General","Canal général",new Date(),new Date(),null,null),
                new Canal(null,"#Room1","Room 1",new Date(),new Date(),null,null),
                new Canal(null,"#Room2","Room 2",new Date(),new Date(),null,null)
        ).forEach(canal -> canalRepo.save(canal));
*/
    }

}
