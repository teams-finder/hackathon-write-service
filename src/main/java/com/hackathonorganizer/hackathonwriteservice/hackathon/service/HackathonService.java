package com.hackathonorganizer.hackathonwriteservice.hackathon.service;

import com.hackathonorganizer.hackathonwriteservice.hackathon.exception.HackathonException;
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

    public HackathonDto updateHackathonData(Hackathon updatedHackathon) {

        Hackathon hackathon = hackathonRepository.findById(updatedHackathon.getId())
                .orElseThrow(() -> new HackathonException("Hackathon with id: " + updatedHackathon.getId() + " not found",
                        HttpStatus.NOT_FOUND));

        hackathon.setName(updatedHackathon.getName());
        hackathon.setDescription(updatedHackathon.getDescription());
        hackathon.setOrganizerInfo(updatedHackathon.getOrganizerInfo());
        hackathon.setEventStartDate(updatedHackathon.getEventStartDate());
        hackathon.setEventEndDate(updatedHackathon.getEventEndDate());

        Hackathon savedHackathon = hackathonRepository.save(hackathon);

        log.info("Hackathon with id: " + savedHackathon.getId() + " updated successfully");

        return mapHackathonToDto(savedHackathon);
    }

    public String deactivateHackathon(Long hackathonId) {

        Hackathon hackathon = hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> new HackathonException("Hackathon with id: " + hackathonId + " not found",
                        HttpStatus.NOT_FOUND));

        hackathon.setActive(false);

        hackathonRepository.save(hackathon);

        log.info("Hackathon with id: " + hackathonId + " deactivated successfully");

        return "Hackathon deactivated successfully";
    }

    private HackathonDto mapHackathonToDto(Hackathon hackathon) {

        return HackathonDto.builder().id(hackathon.getId()).name(hackathon.getName())
                .description(hackathon.getDescription()).build();
    }
}
