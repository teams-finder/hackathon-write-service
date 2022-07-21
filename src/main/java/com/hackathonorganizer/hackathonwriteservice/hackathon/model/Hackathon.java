package com.hackathonorganizer.hackathonwriteservice.hackathon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackathonorganizer.hackathonwriteservice.team.model.Team;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Hackathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String description;

    @NotEmpty
    private String organizerInfo;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventStartDate;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventEndDate;

    @OneToMany(mappedBy = "hackathon")
    private List<Team> teams;
}
