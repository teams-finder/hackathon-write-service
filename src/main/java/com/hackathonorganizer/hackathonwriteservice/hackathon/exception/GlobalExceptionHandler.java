package com.hackathonorganizer.hackathonwriteservice.hackathon.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HackathonException.class)
    public ResponseEntity<ErrorResponse> handleHackathonException(HackathonException ex, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), List.of(ex.getMessage()));

        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }
}