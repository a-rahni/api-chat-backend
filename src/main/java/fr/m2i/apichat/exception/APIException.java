package fr.m2i.apichat.exception;

import fr.m2i.apichat.configuration.swagger.ExceptionErrorCode;

public abstract class APIException extends Exception {
    APIException(String msg) {
        super(msg);
    }

    /**
     * Return a unique error code that identifies this error.
     */
    public String getCode() {
        return ExceptionErrorCode.of(getClass()).name();
    }
}