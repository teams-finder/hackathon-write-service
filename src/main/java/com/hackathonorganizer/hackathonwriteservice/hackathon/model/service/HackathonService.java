package com.hackathonorganizer.hackathonwriteservice.hackathon.model.service;

import com.hackathonorganizer.hackathonwriteservice.hackathon.model.Hackathon;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.repository.HackathonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HackathonService {

    private final HackathonRepository hackathonRepository;

    public Hackathon createHackathon(Hackathon hackathon) {
        return hackathonRepository.save(hackathon);
    }
}
