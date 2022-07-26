package com.hackathonorganizer.hackathonwriteservice.hackathon.creator;

import com.hackathonorganizer.hackathonwriteservice.hackathon.model.Hackathon;
import com.hackathonorganizer.hackathonwriteservice.hackathon.repository.HackathonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Transactional
@RequiredArgsConstructor
public class HackathonCreator {

    private final HackathonRepository hackathonRepository;

    public Hackathon createHackathon() {

        String name = "Hackathon edited";
        String description = "Hackathon desc edited";
        String organizerInfo = "Organizer info";
        LocalDateTime eventStartDate = LocalDateTime.now();
        LocalDateTime eventEndDate = LocalDateTime.now().plusDays(1);

        return hackathonRepository.save(
                Hackathon.builder()
                        .name(name)
                        .description(description)
                        .organizerInfo(organizerInfo)
                        .eventStartDate(eventStartDate)
                        .eventEndDate(eventEndDate)
                        .build()
        );
    }

}
