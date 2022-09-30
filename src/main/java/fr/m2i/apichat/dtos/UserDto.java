package fr.m2i.apichat.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private final Long id;
    private final String username;
    @Email(message = "saisir un format email valide svp", regexp = ".+@.+\\..+")
    private final String email;
}
