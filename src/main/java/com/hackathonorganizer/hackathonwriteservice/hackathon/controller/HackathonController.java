package com.hackathonorganizer.hackathonwriteservice.hackathon.controller;

import com.hackathonorganizer.hackathonwriteservice.hackathon.model.Hackathon;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.dto.HackathonResponse;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.dto.HackathonRequest;
import com.hackathonorganizer.hackathonwriteservice.hackathon.service.HackathonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/hackathons")
@AllArgsConstructor
public class HackathonController {

    private final HackathonService hackathonService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public HackathonResponse createHackathon(@RequestBody @Valid HackathonRequest hackathon) {

        return hackathonService.createHackathon(hackathon);
    }

    @PutMapping
    public HackathonResponse updateHackathonInfo(@PathVariable("id") Long hackathonId,
                                                 @RequestBody @Valid HackathonRequest hackathonRequest) {

        return hackathonService.updateHackathonData(hackathonId, hackathonRequest);
    }

    @PatchMapping("/{id}/deactivate")
    public String deactivateHackathon(@PathVariable("id") Long hackathonId) {

        return hackathonService.deactivateHackathon(hackathonId);
    }

    @PatchMapping("/{id}/participants")
    public String signUpUserToHackathon(@PathVariable("id") Long hackathonId, @RequestParam Long userId) {

        return hackathonService.assignUserToHackathon(hackathonId, userId);
    }

    @PatchMapping("/{id}/participants/remove")
    public String removeUserFromHackathon(@PathVariable("id") Long hackathonId, @RequestParam Long userId) {

        return hackathonService.removeUserFromHackathonParticipants(hackathonId, userId);
    }
}
