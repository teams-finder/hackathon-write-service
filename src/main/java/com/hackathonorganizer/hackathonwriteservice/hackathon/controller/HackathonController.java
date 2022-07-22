package com.hackathonorganizer.hackathonwriteservice.hackathon.controller;

import com.hackathonorganizer.hackathonwriteservice.hackathon.model.Hackathon;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.HackathonDto;
import com.hackathonorganizer.hackathonwriteservice.hackathon.service.HackathonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hackathon")
@AllArgsConstructor
public class HackathonController {

    private final HackathonService hackathonService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<HackathonDto> createHackathon(@RequestBody Hackathon hackathon) {

        System.out.println(hackathon.toString());

        HackathonDto hackathonDto = hackathonService.createHackathon(hackathon);

        return new ResponseEntity<>(hackathonDto, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<HackathonDto> updateHackathonInfo(@RequestBody Hackathon hackathon) {

        HackathonDto hackathonDto = hackathonService.updateHackathonData(hackathon);

        return new ResponseEntity<>(hackathonDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateHackathon(@PathVariable("id") Long hackathonId) {

        System.out.println(hackathonId);

        String hackathonStatus = hackathonService.deactivateHackathon(hackathonId);

        return new ResponseEntity<>("hackathonStatus", HttpStatus.ACCEPTED);
    }
}
