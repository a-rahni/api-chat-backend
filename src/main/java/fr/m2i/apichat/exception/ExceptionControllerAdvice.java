package fr.m2i.apichat.exception;

import fr.m2i.apichat.controller.CanalController;
import fr.m2i.apichat.response.ErrorResponseEntity;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;
/*
le controller advice est testé uniquement avec le controller canal.
pour chaque exception créer une classe
eviter de capturer des exception general(exception) dans canal api
To do: gerer l'exception la reponse 500 pour une exception error server
comment passer le path en param pour plus d'info
*/
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

     @ExceptionHandler(value ={AlreadyExistsException.class})
     @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleAlreadyExistsException( AlreadyExistsException e){
        return ErrorResponseEntity.build(e.getMessage(), 400, "/v1/ressourcexxx", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler (value = {NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        return ErrorResponseEntity.build(e.getMessage(), 404, "/v1/ressourcexxx/id", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler (value = {BadRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
        return ErrorResponseEntity.build(e.getMessage(), 400, "/v1/ressourcexxx/id", HttpStatus.BAD_REQUEST);
    }


    /*
    @ExceptionHandler (value = {NoSuchElementException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        return ErrorResponseEntity.build("element non found", 404, "/v1/ressourcexxx/id", HttpStatus.NOT_FOUND);
    }
    */


}
