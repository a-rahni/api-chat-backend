package fr.m2i.apichat.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CanalDto implements Serializable {
    private Long id;
    @NotEmpty(message = "Le nom est obligatoire... ")
    private String name;
    private String description;
    private Date updatedAt;
}
