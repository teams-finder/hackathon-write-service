package com.hackathonorganizer.hackathonwriteservice.team.controller;

import com.hackathonorganizer.hackathonwriteservice.team.model.Team;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TeamRequest;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TeamSavedResponse;
import com.hackathonorganizer.hackathonwriteservice.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
@Slf4j
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public LocalDateTime test() {
        return LocalDateTime.now();
    }

    @PostMapping
    public TeamSavedResponse create(@RequestBody TeamRequest teamRequest) {
        log.info("Processing new creating team request {}", teamRequest);
        return teamService.create(teamRequest);
    }
}
