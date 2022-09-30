package fr.m2i.apichat.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/*
    Permettre de centraliser  la gestion des  multiples  exceptions
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={ApiRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException ex, WebRequest req){
        //1.Create Payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(ex.getMessage(),
                badRequest,
                new Date(),
                req.getDescription(false));
        //2. Renvoyer response entity
        return new ResponseEntity<>(apiException, badRequest);

    }
    //Gérer Tous les 404
    @ExceptionHandler(value = { ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleNotFound(ResourceNotFoundException ne,WebRequest req){
        HttpStatus notFound=HttpStatus.NOT_FOUND;
        ApiException apiException=new ApiException(ne.getMessage(),
                notFound,
               new Date(),
                req.getDescription(false));
        return new ResponseEntity<>(apiException,notFound);

    }
    //Gérer Tous les 500
    @ExceptionHandler(value = { NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleInternalError(RuntimeException ex, WebRequest req){
        HttpStatus internalServerError=HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException=new ApiException(ex.getMessage(),
                internalServerError,
                new Date(),
                req.getDescription(false));
        return new ResponseEntity<>(apiException,internalServerError);

    }
    //On peut Ajouter  ici les exceptions en fonction





}
