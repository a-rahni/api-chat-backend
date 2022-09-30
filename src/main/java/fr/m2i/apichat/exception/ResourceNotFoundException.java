package fr.m2i.apichat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Long id) {
        super(String.format("Canal with Id %d not found", id));
    }
}
