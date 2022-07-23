package com.hackathonorganizer.hackathonwriteservice.team.utils;

import com.hackathonorganizer.hackathonwriteservice.team.model.Team;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TeamSavedResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TeamMapper {

    public static TeamSavedResponse toResponse(Team team) {
        return new TeamSavedResponse(
                team.getId(),
                team.getOwnerId(),
                team.getHackathon().getId(),
                team.getTeamMembersIds(),
                team.getTags());
    }
}
