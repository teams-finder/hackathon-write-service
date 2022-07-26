package com.hackathonorganizer.hackathonwriteservice.hackathon.controller;

import com.hackathonorganizer.hackathonwriteservice.HackathonPostgresqlContainer;
import com.hackathonorganizer.hackathonwriteservice.hackathon.IntegrationTest;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.dto.HackathonRequest;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class HackathonControllerIntegrationTests extends IntegrationTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer =
            HackathonPostgresqlContainer.getInstance();

    @Test
    void shouldCreateNewHackathon() throws Exception {
        // given
        String url = "/api/v1/hackathon";
        String name = "Hackathon";
        String desc = "Hackathon desc";
        String organizerInfo = "Organizer info";
        LocalDateTime eventStartDate = LocalDateTime.now();
        LocalDateTime eventEndDate = LocalDateTime.now().plusDays(1);

        HackathonRequest request = new HackathonRequest(1L, name, desc,
                organizerInfo, eventStartDate, eventEndDate);


        // when
        ResultActions resultActions = mockMvc.perform(postJsonRequest(url,
                request));

        // then
        resultActions.andExpect(status().isCreated());
    }

}
