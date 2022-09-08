package fr.m2i.apichat.dto;

import fr.m2i.apichat.model.Message;
import fr.m2i.apichat.model.User;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class CanalDTO {
    private Long id;
    private  String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private List<User> users=new ArrayList<>();
    private List<Message> messages=new ArrayList<>();

}
