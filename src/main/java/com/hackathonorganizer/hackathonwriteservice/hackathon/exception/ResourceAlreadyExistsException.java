package com.hackathonorganizer.hackathonwriteservice.hackathon.exception;

public class ResourceAlreadyExistsException extends RuntimeException{

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
