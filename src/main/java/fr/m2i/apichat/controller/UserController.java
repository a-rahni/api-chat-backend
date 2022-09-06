package fr.m2i.apichat.controller;

import fr.m2i.apichat.model.User;
import fr.m2i.apichat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService =userService;
    }

    @GetMapping()
    public ResponseEntity<Object> getAllUsers(){

        //List<User> users = userService.findAll();
        return null;

    }


}
