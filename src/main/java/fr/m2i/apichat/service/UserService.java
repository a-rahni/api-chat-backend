package fr.m2i.apichat.service;

import fr.m2i.apichat.dto.UserMapper;
import fr.m2i.apichat.exception.NotFoundException;
import fr.m2i.apichat.model.User;
import fr.m2i.apichat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo){
        this.repo = repo;
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User findById(Long id) {
        return repo.findById(id).orElseThrow(()->
        {throw new NotFoundException("User with id: " +id+ " was not found");
        });
    }

    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public User update(Long id, User userContent) {
        User found= findById(id);
        //UserMapper.copy(found,userContent);
        return repo.save(found);

    }

    @Override
    public void delete(Long id) {
        User found= findById(id);
        repo.delete(found);
    }
}
