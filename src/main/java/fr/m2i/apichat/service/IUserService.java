package fr.m2i.apichat.service;

import fr.m2i.apichat.model.User;

import java.util.List;

public interface IUserService {

    public List<User> findAll();
    public User findById(Long id);
    public User save(User user);
    public User update(Long id, User userContent);
    public void delete(Long id);

}
