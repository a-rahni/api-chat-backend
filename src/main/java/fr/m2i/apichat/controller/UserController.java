package fr.m2i.apichat.controller;

import fr.m2i.apichat.dto.CanalDTO;
import fr.m2i.apichat.dto.CanalMapper;
import fr.m2i.apichat.dto.UserDTO;
import fr.m2i.apichat.dto.UserMapper;
import fr.m2i.apichat.exception.AlreadyExistsException;
import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.User;
import fr.m2i.apichat.response.ErrorResponseEntity;
import fr.m2i.apichat.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "Returns the list of all users", nickname = "Get all users", response = UserDTO.class)
    public ResponseEntity<Object> getAllUsers(){
        List<User> users = userService.findAll();
        List< UserDTO> usersDTO = UserMapper.buidListUserDTO(users);
        return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
    }


    //To DO: fin by id
    // find by username
    // find by email


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a user", nickname = "Create a user", response = UserDTO.class)
    public ResponseEntity<Object> createUser(@RequestBody UserDTO userDto) {

        try {
            User toCreate = UserMapper.buildUser(userDto);
            User created = userService.save(toCreate);
            UserDTO createdDTO = UserMapper.buildUserDTO(created);
            return ResponseEntity.status(HttpStatus.OK).body(createdDTO);
        }catch (AlreadyExistsException ae){
            return ErrorResponseEntity.build("the user name or email is already used", 400, "/v1/users", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println("******"+ e.getMessage());
            return ErrorResponseEntity.build("An error occured", 500, "/v1/users", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
