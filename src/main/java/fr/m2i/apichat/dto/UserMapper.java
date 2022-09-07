package fr.m2i.apichat.dto;


import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.Message;
import fr.m2i.apichat.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {



    public static  UserDTO buildUserDTO(User user){

        if(user == null){
            return new UserDTO();
        }
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                null
        );
    }

    public static List<UserDTO> buidListUserDTO(List<User> users){

        List<UserDTO> dtos = new ArrayList<>();
        for(User u:users){
            UserDTO dto = UserMapper.buildUserDTO(u);
            dtos.add(dto);
        }
        return dtos;
    }

    public static  User buildUser(UserDTO userDTO){

        if(userDTO == null){
            return null;
        }
        User user= new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return user;
    }

    public static User  copy(User user, User userContent){
        if(user == null || userContent ==null){
            return null;
        }

        if(userContent.getUsername() != null){
            user.setUsername(userContent.getUsername());
        }

        if(userContent.getEmail() != null){
            user.setEmail(userContent.getEmail());
        }

        if(userContent.getPassword()!=null && !"".equals(userContent.getPassword())){
            user.setPassword(userContent.getPassword());
        }

        if(userContent.getCanaux()!=null){
            user.setCanaux(userContent.getCanaux());
        }

        if(user.getMessages() != null){
            user.setMessages(userContent.getMessages());
        }

        return user;

    }


}
