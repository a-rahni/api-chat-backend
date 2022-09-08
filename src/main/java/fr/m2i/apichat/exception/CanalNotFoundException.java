package fr.m2i.apichat.exception;

public class CanalNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CanalNotFoundException(Long id) {
        super(String.format("Canal with Id %d not found", id));
    }
}
