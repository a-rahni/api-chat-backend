package fr.m2i.apichat.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class MessageDto implements Serializable {
    private final Long id;
    @NotNull(message = "Message ne peut pas être vide")
    private final String content;
    private final Date updatedAt;
}
