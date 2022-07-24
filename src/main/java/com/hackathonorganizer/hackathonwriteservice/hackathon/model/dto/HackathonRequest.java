package com.hackathonorganizer.hackathonwriteservice.hackathon.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record HackathonRequest(

        @NotNull
        Long id,

        @NotEmpty
        String name,

        @NotEmpty
        String description,

        @NotEmpty
        String organizerInfo,

        @NotNull
        @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
        LocalDateTime eventStartDate,

        @NotNull
        @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
        LocalDateTime eventEndDate
) {
}
