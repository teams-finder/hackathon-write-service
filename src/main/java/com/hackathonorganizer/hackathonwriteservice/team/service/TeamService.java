package com.hackathonorganizer.hackathonwriteservice.team.service;

import com.hackathonorganizer.hackathonwriteservice.hackathon.model.Hackathon;
import com.hackathonorganizer.hackathonwriteservice.hackathon.repository.HackathonRepository;
import com.hackathonorganizer.hackathonwriteservice.team.exception.ResourceNotFoundException;
import com.hackathonorganizer.hackathonwriteservice.team.model.Tag;
import com.hackathonorganizer.hackathonwriteservice.team.model.Team;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TagRequest;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TeamRequest;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TeamSavedResponse;
import com.hackathonorganizer.hackathonwriteservice.team.repository.TagRepository;
import com.hackathonorganizer.hackathonwriteservice.team.repository.TeamRepository;
import com.hackathonorganizer.hackathonwriteservice.team.utils.TeamMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamService {


    private final TeamRepository teamRepository;
    private final TagService tagService;
    //TODO change to service when it will be ready
    private final HackathonRepository hackathonRepository;

    public TeamSavedResponse create(TeamRequest teamRequest) {
        val teamToSave = Team.builder()
                .ownerId(teamRequest.ownerId())
                .hackathon(getHackathon(teamRequest.hackathonId()))
                .teamMembersIds(teamRequest.teamMembersIds())
                .tags(getTags(teamRequest.tags()))
                .build();
        log.info("Trying to save new team {}", teamToSave);
        return TeamMapper.toResponse(teamRepository.save(teamToSave));
    }

    private List<Tag> getTags(List<TagRequest> tags) {
        val tagsToSave = new ArrayList<Tag>();
        for (TagRequest tagRequest : tags) {
            if (tagService.existsByName(tagRequest.name())) {
                tagsToSave.add(tagService.findByName(tagRequest.name()));
            } else {
                tagsToSave.add(tagService.create(tagRequest));
            }
        }
        return tagsToSave;
    }

    private Hackathon getHackathon(Long hackathonId) {
        return hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> {
                    log.error("Hacathon id = {} not found", hackathonId);
                    throw new ResourceNotFoundException(String.format("Hacathon id = %d not found", hackathonId));
                });
    }
}
