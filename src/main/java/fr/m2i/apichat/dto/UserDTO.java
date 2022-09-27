package fr.m2i.apichat.dto;

import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    //private List<CanalDTO> canaux;
    //private List<MessageDTO> messages;

}
