package fr.m2i.apichat.dto;

import fr.m2i.apichat.model.Message;
import fr.m2i.apichat.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
public class CanalDTO {

    private Long id;
    private  String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private List<UserDTO> users=new ArrayList<>();
    private List<MessageDTO> messages=new ArrayList<>();

}
