package com.hackathonorganizer.hackathonwriteservice.hackathon.service;

import com.hackathonorganizer.hackathonwriteservice.hackathon.exception.ResourceNotFoundException;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.Hackathon;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.HackathonDto;
import com.hackathonorganizer.hackathonwriteservice.hackathon.repository.HackathonRepository;
import com.hackathonorganizer.hackathonwriteservice.hackathon.utils.HackathonMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class HackathonService {

    private final HackathonRepository hackathonRepository;

    public HackathonDto createHackathon(Hackathon hackathon) {

        Hackathon savedHackathon = hackathonRepository.save(hackathon);

        log.info("Hackathon with id: " + savedHackathon.getId() + " saved successfully");

        return HackathonMapper.mapHackathonToDto(savedHackathon);
    }

    public HackathonDto updateHackathonData(Hackathon hackathonUpdatedData) {

        Hackathon hackathon = hackathonRepository.findById(hackathonUpdatedData.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("Hackathon with id: %d not found", hackathonUpdatedData.getId())));

        Hackathon.HackathonBuilder hackathonBuilder = hackathon.toBuilder();

        Hackathon updatedHackathon = hackathonBuilder
                .name(hackathonUpdatedData.getName())
                .description(hackathonUpdatedData.getDescription())
                .organizerInfo(hackathonUpdatedData.getOrganizerInfo())
                .eventStartDate(hackathonUpdatedData.getEventStartDate())
                .eventEndDate(hackathonUpdatedData.getEventEndDate())
                .build();


        Hackathon savedHackathon = hackathonRepository.save(updatedHackathon);

        log.info("Hackathon with id: " + savedHackathon.getId() + " updated successfully");

        return HackathonMapper.mapHackathonToDto(savedHackathon);
    }

    public String deactivateHackathon(Long hackathonId) {

        Hackathon hackathon = hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Hackathon with id: %d not found", hackathonId)));

        hackathon.setActive(false);

        hackathonRepository.save(hackathon);

        log.info("Hackathon with id: " + hackathonId + " deactivated successfully");

        return "Hackathon deactivated successfully";
    }

    public String assignUserToHackathon(Long hackathonId, Long userId) {

        Hackathon hackathon = hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Hackathon with id: %d not found", hackathonId)));

        hackathon.addUserToHackathonParticipants(userId);

        hackathonRepository.save(hackathon);

        log.info("User with id: " + userId + " successfully added to hackathon with id: " + hackathonId);

        return "User successfully assigned to " + hackathon.getName() + " hackathon";
    }

    public String removeUserFromHackathonParticipants(Long hackathonId, Long userId) {
        Hackathon hackathon = hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Hackathon with id: %d not found", hackathonId)));

        System.out.println(hackathon.getHackathonParticipantsIds());

        hackathon.removeUserFromHackathonParticipants(userId);

        hackathonRepository.save(hackathon);

        log.info("User with id: " + userId + " successfully removed from hackathon with id: " + hackathonId);

        return "User successfully removed from " + hackathon.getName() + " hackathon";
    }
}
