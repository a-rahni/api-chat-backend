package fr.m2i.apichat.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;
import java.util.Date;

@ApiModel(description = "Information au sujet des erreurs de api-Chat")
public class ApiException {
    @ApiModelProperty("Message d'erreurs")
    private final String message;
    @ApiModelProperty("Https Status")
    private final HttpStatus httpStatus;
    @ApiModelProperty("Date ")
    private final Date timestamp;
    @ApiModelProperty("Description de l'erreur")
    private String description;

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public ApiException(String message, HttpStatus httpStatus, Date timestamp, String description) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.description = description;
    }

}
