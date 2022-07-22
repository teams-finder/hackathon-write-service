package com.hackathonorganizer.hackathonwriteservice.hackathon.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HackathonDto {
    private Long id;
    private String name;
    private String description;
}
