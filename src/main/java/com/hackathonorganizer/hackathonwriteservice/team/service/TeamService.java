package com.hackathonorganizer.hackathonwriteservice.team.service;

import com.hackathonorganizer.hackathonwriteservice.hackathon.model.Hackathon;
import com.hackathonorganizer.hackathonwriteservice.hackathon.repository.HackathonRepository;
import com.hackathonorganizer.hackathonwriteservice.team.exception.ResourceNotFoundException;
import com.hackathonorganizer.hackathonwriteservice.team.model.Tag;
import com.hackathonorganizer.hackathonwriteservice.team.model.Team;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TagRequest;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TeamRequest;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TeamResponse;
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

    public TeamResponse create(TeamRequest teamRequest) {
        val teamToSave = Team.builder()
                .ownerId(teamRequest.ownerId())
                .hackathon(getHackathon(teamRequest.hackathonId()))
                .teamMembersIds(teamRequest.teamMembersIds())
                .tags(getTags(teamRequest.tags()))
                .build();
        log.info("Trying to save new team {}", teamToSave);
        return TeamMapper.toResponse(teamRepository.save(teamToSave));
    }

    public TeamResponse editById(Long id, TeamRequest teamRequest) {
        return teamRepository.findById(id)
                .map(teamToEdit -> {
                    teamToEdit.setOwnerId(teamRequest.ownerId());
                    teamToEdit.setHackathon(getHackathon(teamRequest.hackathonId()));
                    teamToEdit.setTeamMembersIds(teamRequest.teamMembersIds());
                    teamToEdit.setTags(getTags(teamRequest.tags()));
                    return TeamMapper.toResponse(teamRepository.save(teamToEdit));
                }).orElseThrow(() -> {
                    log.error(String.format("Team id = %d not found", id));
                    return new ResourceNotFoundException(String.format("Team id = %d not found", id));
                });
    }

    public TeamResponse editPartialById(Long id, TeamRequest teamRequest) {
        return teamRepository.findById(id)
                .map(teamToEdit -> {
                    if (teamRequest.ownerId() != null) {
                        teamToEdit.setOwnerId(teamRequest.ownerId());
                    }
                    if (teamRequest.hackathonId() != null) {
                        teamToEdit.setHackathon(getHackathon(teamRequest.hackathonId()));
                    }
                    if (teamRequest.teamMembersIds() != null) {
                        teamToEdit.setTeamMembersIds(teamRequest.teamMembersIds());
                    }
                    if (teamRequest.tags() != null) {
                        teamToEdit.setTags(getTags(teamRequest.tags()));
                    }
                    return TeamMapper.toResponse(teamRepository.save(teamToEdit));
                }).orElseThrow(() -> {
                    log.error(String.format("Team id = %d not found", id));
                    return new ResourceNotFoundException(String.format("Team id = %d not found", id));
                });
    }


    private List<Tag> getTags(List<TagRequest> tags) {
        val tagsToSave = new ArrayList<Tag>();
        tags.forEach(tag -> tagsToSave.add(tagService.existsByName(tag.name()) ? tagService.findByName(tag.name()) : tagService.create(tag)));
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
