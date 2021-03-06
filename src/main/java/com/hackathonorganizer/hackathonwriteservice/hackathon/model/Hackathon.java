package com.hackathonorganizer.hackathonwriteservice.hackathon.model;

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
    private LocalDateTime eventStartDate;

    @NotNull
    private LocalDateTime eventEndDate;

    @OneToMany(mappedBy = "hackathon")
    private List<Team> teams;
}
