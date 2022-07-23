package com.hackathonorganizer.hackathonwriteservice.team.model.dto;

import com.hackathonorganizer.hackathonwriteservice.team.model.Team;

import java.util.List;
import java.util.Set;

public record TeamRequest(
        Long ownerId,
        Long hackathonId,
        Set<Long> teamMembersIds,
        List<TagRequest> tags
) {
}
