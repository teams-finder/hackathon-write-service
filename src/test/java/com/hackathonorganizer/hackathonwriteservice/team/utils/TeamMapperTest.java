package com.hackathonorganizer.hackathonwriteservice.team.utils;

import com.github.javafaker.Faker;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.Hackathon;
import com.hackathonorganizer.hackathonwriteservice.team.model.Tag;
import com.hackathonorganizer.hackathonwriteservice.team.model.Team;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TagRequest;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TeamRequest;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TeamResponse;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeamMapperTest {

    Faker faker = new Faker((new Locale("PL")));

    @Test
    void shouldReturnMappedTeamToTeamResponse() {
        //given
        val teamMembersIds = Stream.of(1L, 2L, 3L).collect(Collectors.toSet());
        val tags = Stream.of(
                        Tag.builder()
                                .name("java")
                                .build(),
                        Tag.builder()
                                .name("java")
                                .build())
                .toList();
        val hackathon = Hackathon.builder()
                .id(1L)
                .description(faker.chuckNorris().toString())
                .organizerInfo(faker.chuckNorris().toString())
                .eventStartDate(LocalDateTime.ofInstant(faker.date().future(2, TimeUnit.DAYS).toInstant(), ZoneId.systemDefault()))
                .eventEndDate(LocalDateTime.ofInstant(faker.date().future(4, TimeUnit.DAYS).toInstant(), ZoneId.systemDefault()))
                .build();
        val team = Team.builder()
                .id(1L)
                .ownerId(1L)
                .hackathon(hackathon)
                .teamMembersIds(teamMembersIds)
                .tags(tags)
                .build();
        val expectedTeamResponse = new TeamResponse(
                1L,
                1L,
                1L,
                teamMembersIds,
                tags);
        //when
        val mappedTeam = TeamMapper.toResponse(team);
        //then
        assertNotNull(mappedTeam);
        assertInstanceOf(TeamResponse.class, mappedTeam);
        assertEquals(expectedTeamResponse, mappedTeam);
    }

}