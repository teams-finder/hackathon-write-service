package com.hackathonorganizer.hackathonwriteservice.hackathon.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record HackathonDto(@NotNull Long id, @NotEmpty String name, @NotEmpty String description) {
}
