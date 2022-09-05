package fr.m2i.apichat.dto;

import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.Message;
import fr.m2i.apichat.model.User;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

public class MessageMapper {

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
