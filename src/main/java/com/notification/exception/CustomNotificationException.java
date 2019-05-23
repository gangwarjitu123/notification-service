package com.notification.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomNotificationException extends RuntimeException {
    private String errorMessage;
    private Integer status;
    private HttpStatus httpStatus;

    public CustomNotificationException(String errorMessage, Integer status, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public CustomNotificationException(String message, String errorMessage, Integer status, HttpStatus httpStatus) {
        super(message);
        this.errorMessage = errorMessage;
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public CustomNotificationException(String message, Throwable cause, String errorMessage, Integer status, HttpStatus httpStatus) {
        super(message, cause);
        this.errorMessage = errorMessage;
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public CustomNotificationException(Throwable cause, String errorMessage, Integer status, HttpStatus httpStatus) {
        super(cause);
        this.errorMessage = errorMessage;
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public CustomNotificationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorMessage, Integer status, HttpStatus httpStatus) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorMessage = errorMessage;
        this.status = status;
        this.httpStatus = httpStatus;
    }
}
