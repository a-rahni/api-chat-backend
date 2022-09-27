package fr.m2i.apichat.dto;

import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.Message;
import fr.m2i.apichat.model.User;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

public class MessageMapper {

    public static MessageDTO buildMessageDTO(Message message){
        if(message == null){
            return new MessageDTO();
        }

        UserDTO userDTO = null; // eviter de reboucler

        if(message.getUser() != null){
            userDTO = UserMapper.buildUserDTO(message.getUser());
        }

        CanalDTO canalDTO = null; // eviter de reboucler / message est retourner dans un canal

        return new MessageDTO(
                message.getId(),
                message.getContent(),
                message.getCreatedAt(),
                message.getUpdatedAt(),
                userDTO,
                null//canalDTO,
                //userDTO.getUsername()
        );
    }

    public static Message buildMessage(MessageDTO messageDTO){
        if(messageDTO == null){
            return new Message();
        }

        User user = null;

        if(messageDTO.getUser() != null && messageDTO.getUser().getId() != null){
            user = new User();
            user.setId((messageDTO.getUser().getId()));
        }

        Canal canal = null;
        if(messageDTO.getCanal() != null && messageDTO.getCanal().getId() != null){
            canal = new Canal();
            canal.setId((messageDTO.getCanal().getId()));
        }

        return new Message(
                messageDTO.getId(),
                messageDTO.getContent(),
                messageDTO.getCreatedAt(),
                messageDTO.getUpdatedAt(),
                user,
                canal
        );
    }

    public static Message copy(Message message, Message content){
        if(message == null || content == null){
            return null;
        }
        if(content.getContent()!=null){
            message.setContent(content.getContent());
        }

        // CreatedAtTime , time : non modifiable

        if(content.getUpdatedAt()!= null){
            message.setUpdatedAt(content.getUpdatedAt());
        }

        // user and canal non modifiable pour un message

        return message;

    }
}
