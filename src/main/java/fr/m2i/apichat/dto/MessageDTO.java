package fr.m2i.apichat.dto;

import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Setter  @Getter
public class MessageDTO {

    private Long id;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    private UserDTO user;
    private CanalDTO canal;

    //private String username;


}
