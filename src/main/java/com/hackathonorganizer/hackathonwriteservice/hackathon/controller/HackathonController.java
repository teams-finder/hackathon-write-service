package com.hackathonorganizer.hackathonwriteservice.hackathon.controller;

import com.hackathonorganizer.hackathonwriteservice.hackathon.model.Hackathon;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.HackathonDto;
import com.hackathonorganizer.hackathonwriteservice.hackathon.service.HackathonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hackathon")
@AllArgsConstructor
public class HackathonController {

    private final HackathonService hackathonService;

    @PostMapping(consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public HackathonDto createHackathon(@RequestBody Hackathon hackathon) {

        System.out.println(hackathon.toString());

        HackathonDto hackathonDto = hackathonService.createHackathon(hackathon);

        return hackathonDto;
    }

    @PatchMapping
    public HackathonDto updateHackathonInfo(@RequestBody Hackathon hackathon) {

        HackathonDto hackathonDto = hackathonService.updateHackathonData(hackathon);

        return hackathonDto;
    }

    @PatchMapping("/{id}/deactivate")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public String deactivateHackathon(@PathVariable("id") Long hackathonId) {

        return hackathonService.deactivateHackathon(hackathonId);
    }
}
