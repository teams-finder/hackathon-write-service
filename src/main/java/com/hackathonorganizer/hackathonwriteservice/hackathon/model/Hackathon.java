package com.hackathonorganizer.hackathonwriteservice.hackathon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackathonorganizer.hackathonwriteservice.hackathon.exception.ResourceAlreadyExistsException;
import com.hackathonorganizer.hackathonwriteservice.hackathon.exception.ResourceNotFoundException;
import com.hackathonorganizer.hackathonwriteservice.team.model.Team;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
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

    @ElementCollection
    @CollectionTable(name = "hackathon_participants", joinColumns = @JoinColumn(name = "hackathon_id"))
    @Column(name = "participant_id")
    private Set<Long> hackathonParticipantsIds;

    public void addUserToHackathonParticipants(Long userId) {

        if (!hackathonParticipantsIds.contains(userId)) {
            hackathonParticipantsIds.add(userId);
        } else {
            log.error(String.format("Participant with id: %d is already added", userId));
            throw new ResourceAlreadyExistsException(String.format("Participant with id: %d is already added", userId));
        }
    }

    public void removeUserFromHackathonParticipants(Long userId) {

        if (hackathonParticipantsIds.contains(userId)) {
            hackathonParticipantsIds.remove(userId);
        } else {
            log.error(String.format("Participant with id: %d not found", userId));
            throw new ResourceNotFoundException(String.format("Participant with id: %d not found", userId));
        }
    }
}
