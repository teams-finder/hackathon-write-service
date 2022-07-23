package com.hackathonorganizer.hackathonwriteservice.team.model.dto;

import com.hackathonorganizer.hackathonwriteservice.team.model.Tag;

import java.util.List;
import java.util.Set;

public record TeamSavedResponse(
        Long id,
        Long ownerId,
        Long hackathonId,
        Set<Long> teamMembersIds,
        List<Tag> tags
) {
}
