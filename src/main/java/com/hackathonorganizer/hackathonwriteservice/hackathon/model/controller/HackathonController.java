package com.hackathonorganizer.hackathonwriteservice.hackathon.model.controller;

import com.hackathonorganizer.hackathonwriteservice.hackathon.model.Hackathon;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.service.HackathonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1/hackathon")
@AllArgsConstructor
public class HackathonController {

    private final HackathonService hackathonService;

    @PostMapping
    public ResponseEntity<Hackathon> createHackathon(@RequestBody Hackathon hackathon) {

        Hackathon h = hackathonService.createHackathon(hackathon);

        return new ResponseEntity<>(h, HttpStatus.CREATED);
    }
}
