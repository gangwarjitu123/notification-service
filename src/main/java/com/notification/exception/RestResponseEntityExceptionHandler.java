package com.notification.exception;


import com.notification.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The type Rest response entity exception handler.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors=ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
       ApiError apiError= ApiError.builder()
                .errorMessage(errors)
                .debugMessage(ex.getLocalizedMessage())
                .build();
        Response response=Response.builder()
                .error(apiError)
                .status("failed")
                .build();
        return new ResponseEntity<>(response,status);
    }


    /**
     * Custom exception response entity.
     *
     * @param customNotificationException the custom notification exception
     * @return the response entity
     */
    @ExceptionHandler(CustomNotificationException.class)
    public ResponseEntity<Object> customException(CustomNotificationException customNotificationException){
        ApiError apiError= ApiError.builder()
                .errorMessage(Arrays.asList(customNotificationException.getErrorMessage()))
                .debugMessage(customNotificationException.getErrorMessage())
                .build();
        Response response=Response.builder()
                .error(apiError)
                .status("failed")
                .build();
        return new ResponseEntity<>(response,customNotificationException.getHttpStatus());
    }
}