package com.hackathonorganizer.hackathonwriteservice.hackathon.controller;

import com.hackathonorganizer.hackathonwriteservice.hackathon.model.Hackathon;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.HackathonDto;
import com.hackathonorganizer.hackathonwriteservice.hackathon.service.HackathonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hackathons")
@AllArgsConstructor
public class HackathonController {

    private final HackathonService hackathonService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public HackathonDto createHackathon(@RequestBody Hackathon hackathon) {

        return hackathonService.createHackathon(hackathon);
    }

    @PatchMapping
    public HackathonDto updateHackathonInfo(@RequestBody Hackathon hackathon) {

        return hackathonService.updateHackathonData(hackathon);
    }

    @PatchMapping("/{id}/deactivate")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public String deactivateHackathon(@PathVariable("id") Long hackathonId) {

        return hackathonService.deactivateHackathon(hackathonId);
    }

    @PostMapping("/{id}/participants")
    public String signUpUserToHackathon(@PathVariable("id") Long hackathonId, @RequestParam Long userId) {

        return hackathonService.assignUserToHackathon(hackathonId, userId);
    }

    @PatchMapping("/{id}/participants")
    public String removeUserFromHackathon(@PathVariable("id") Long hackathonId, @RequestParam Long userId) {

        return hackathonService.removeUserFromHackathonParticipants(hackathonId, userId);
    }
}
