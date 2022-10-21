package fr.m2i.apichat.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
