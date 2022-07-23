package com.hackathonorganizer.hackathonwriteservice.team.service;

import com.hackathonorganizer.hackathonwriteservice.hackathon.repository.HackathonRepository;
import com.hackathonorganizer.hackathonwriteservice.team.model.Team;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TeamRequest;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TeamSavedResponse;
import com.hackathonorganizer.hackathonwriteservice.team.repository.TeamRepository;
import com.hackathonorganizer.hackathonwriteservice.team.utils.TeamMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamService {

    private final TeamRepository teamRepository;
    //TODO change it to service when it will be ready
    private final HackathonRepository hackathonRepository;

    public TeamSavedResponse create(TeamRequest teamRequest) {
        val teamToSave = Team.builder()
                .ownerId(teamRequest.ownerId())
                //TODO exception
                .hackathon(hackathonRepository.findById(teamRequest.hackathonId()).orElseThrow())
                .teamMembersIds(teamRequest.teamMembersIds())
                //TODO tags
                .build();
        log.info("Trying to save new team {}", teamToSave);
        return TeamMapper.toResponse(teamRepository.save(teamToSave));
    }
}
