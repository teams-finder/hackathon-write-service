package com.hackathonorganizer.hackathonwriteservice.hackathon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HackathonControllerAdvice {

    @ExceptionHandler(HackathonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleHackathonNotFoundException(HackathonNotFoundException ex) {

        return ex.getMessage();
    }
}