package com.hackathonorganizer.hackathonwriteservice.hackathon.controller;

import com.hackathonorganizer.hackathonwriteservice.IntegrationTest;
import com.hackathonorganizer.hackathonwriteservice.hackathon.creator.HackathonCreator;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.Hackathon;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.dto.HackathonRequest;
import com.hackathonorganizer.hackathonwriteservice.hackathon.repository.HackathonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class HackathonControllerIntegrationTests extends IntegrationTest {

    @Autowired
    private HackathonRepository hackathonRepository;

    @Autowired
    private HackathonCreator hackathonCreator;

    @BeforeEach
    void setUp() {
        hackathonRepository.deleteAll();
    }

    @Test
    void shouldCreateNewHackathon() throws Exception {
        // given
        String url = "/api/v1/hackathons";

        HackathonRequest request = buildHackathonRequest();

        // when
        ResultActions resultActions = mockMvc.perform(postJsonRequest(url,
                request));

        // then
        resultActions.andExpect(status().isCreated());

        resultActions.andExpect(jsonPath("$.name").value(request.name()));
        resultActions.andExpect(jsonPath("$.description").value(request.description()));
    }

    @Test
    void shouldEditHackathon() throws Exception {
        // given
        String url = "/api/v1/hackathons/";
        Long id = 1L;
        String name = "Hackathon edited";
        String desc = "Hackathon desc edited";
        String organizerInfo = "Organizer info";
        LocalDateTime eventStartDate = LocalDateTime.now();
        LocalDateTime eventEndDate = LocalDateTime.now().plusDays(1);

        HackathonRequest request = new HackathonRequest(name, desc,
                organizerInfo, eventStartDate, eventEndDate);

        Hackathon savedHackathon = hackathonCreator.createHackathon();

        // when
        ResultActions resultActions =
                mockMvc.perform(putJsonRequest(url + savedHackathon.getId(),
                        request));

        // then
        resultActions.andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$.id").value(savedHackathon.getId()));
        resultActions.andExpect(jsonPath("$.name").value(name));
        resultActions.andExpect(jsonPath("$.description").value(desc));
    }

    @Test
    void shouldThrowHackathonNotFound() throws Exception {
        // given
        String url = "/api/v1/hackathons/999";

        HackathonRequest request = buildHackathonRequest();

        // when
        ResultActions resultActions =
                mockMvc.perform(putJsonRequest(url,
                        request));

        // then
        resultActions.andExpect(status().isNotFound());

        resultActions.andExpect(jsonPath("$.message").value("Hackathon with " +
                "id: 999 not found"));
    }


    @Test
    void shouldDeactivateHackathon() throws Exception {
        // given
        String url = "/api/v1/hackathons/";

        Hackathon savedHackathon = hackathonCreator.createHackathon();

        url += savedHackathon.getId() + "/deactivate";

        // when
        ResultActions resultActions = mockMvc.perform(patchJsonRequest(url,
                null));

        // then
        resultActions.andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$").value("Hackathon deactivated successfully"));
    }

    @Test
    void shouldAddUserToHackathonParticipants() throws Exception {
        // given
        String url = "/api/v1/hackathons/";
        String participantId = "9";

        Hackathon savedHackathon = hackathonCreator.createHackathon();

        url += savedHackathon.getId() + "/participants/" + participantId;

        // when
        ResultActions resultActions =
                mockMvc.perform(patchJsonRequest(url,
                        null));

        // then
        resultActions.andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$")
                .value("User successfully assigned to " + savedHackathon.getName() +
                        " hackathon"));
    }


    @Test
    void shouldRemoveUserFromHackathonParticipants() throws Exception {
        // given
        String url = "/api/v1/hackathons/";
        String participantId = "9";

        Hackathon savedHackathon = hackathonCreator.createHackathon();

        url += savedHackathon.getId() + "/participants/" + participantId +
                "/remove";

        // when
        ResultActions resultActions =
                mockMvc.perform(patchJsonRequest(url,
                        null));

        // then
        resultActions.andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$")
                .value("User successfully removed from " + savedHackathon.getName() + " " +
                        "hackathon"));
    }

    private HackathonRequest buildHackathonRequest() {
        String name = "Hackathon";
        String desc = "Hackathon desc";
        String organizerInfo = "Organizer info";
        LocalDateTime eventStartDate = LocalDateTime.now();
        LocalDateTime eventEndDate = LocalDateTime.now().plusDays(1);

        return new HackathonRequest(name, desc,
                organizerInfo, eventStartDate, eventEndDate);
    }

}
