package com.hackathonorganizer.hackathonwriteservice.hackathon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackathonorganizer.hackathonwriteservice.team.model.Team;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hackathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private String organizerInfo;

    private boolean isActive = true;

    @NotNull
    @JsonFormat(pattern="HH:mm:ss dd-MM-yyyy")
    private LocalDateTime eventStartDate;

    @NotNull
    @JsonFormat(pattern="HH:mm:ss dd-MM-yyyy")
    private LocalDateTime eventEndDate;

    @OneToMany(mappedBy = "hackathon")
    private List<Team> teams;
}
