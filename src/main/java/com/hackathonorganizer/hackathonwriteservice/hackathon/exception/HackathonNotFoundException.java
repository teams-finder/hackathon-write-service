package com.hackathonorganizer.hackathonwriteservice.hackathon.exception;

public class HackathonNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Hackathon with id: %d not found";

    public HackathonNotFoundException(Long id) {
        super(MESSAGE.formatted(id));
    }
}
