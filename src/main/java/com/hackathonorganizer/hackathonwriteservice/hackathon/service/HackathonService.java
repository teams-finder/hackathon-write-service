package com.hackathonorganizer.hackathonwriteservice.hackathon.service;

import com.hackathonorganizer.hackathonwriteservice.hackathon.exception.HackathonNotFoundException;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.Hackathon;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.HackathonDto;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.repository.HackathonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class HackathonService {

    private final HackathonRepository hackathonRepository;

    public HackathonDto createHackathon(Hackathon hackathon) {

        Hackathon savedHackathon = hackathonRepository.save(hackathon);

        log.info("Hackathon with id: " + savedHackathon.getId() + " saved successfully");

        return mapHackathonToDto(savedHackathon);
    }

    public HackathonDto updateHackathonData(Hackathon hackatonUpdatedData) {

        Hackathon hackathon = hackathonRepository.findById(hackatonUpdatedData.getId())
                .orElseThrow(() -> new HackathonNotFoundException(hackatonUpdatedData.getId()));

        Hackathon.HackathonBuilder hackathonBuilder = hackathon.toBuilder();

        Hackathon updatedHackathon = hackathonBuilder
                .name(hackatonUpdatedData.getName())
                .description(hackatonUpdatedData.getDescription())
                .organizerInfo(hackatonUpdatedData.getOrganizerInfo())
                .eventStartDate(hackatonUpdatedData.getEventStartDate())
                .eventEndDate(hackatonUpdatedData.getEventEndDate())
                .build();


        Hackathon savedHackathon = hackathonRepository.save(updatedHackathon);

        log.info("Hackathon with id: " + savedHackathon.getId() + " updated successfully");

        return mapHackathonToDto(savedHackathon);
    }

    public String deactivateHackathon(Long hackathonId) {

        Hackathon hackathon = hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> new HackathonNotFoundException(hackathonId));

        hackathon.setActive(false);

        hackathonRepository.save(hackathon);

        log.info("Hackathon with id: " + hackathonId + " deactivated successfully");

        return "Hackathon deactivated successfully";
    }

    private HackathonDto mapHackathonToDto(Hackathon hackathon) {

        return new HackathonDto(hackathon.getId(), hackathon.getName(), hackathon.getDescription());
    }
}
