package com.hackathonorganizer.hackathonwriteservice.team.exception;

import java.time.LocalDateTime;

public record ErrorMessage(String httpStatus, String message, LocalDateTime timeStamp) {
}
