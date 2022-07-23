package com.hackathonorganizer.hackathonwriteservice.team.exception.handler;

import com.hackathonorganizer.hackathonwriteservice.team.exception.ErrorMessage;
import com.hackathonorganizer.hackathonwriteservice.team.exception.ResourceAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ResourceAlreadyExistsExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handle(ResourceAlreadyExistsException e) {
        return new ErrorMessage(
                HttpStatus.CONFLICT.toString(),
                e.getMessage(),
                LocalDateTime.now());
    }

}
