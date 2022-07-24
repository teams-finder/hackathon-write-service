package com.hackathonorganizer.hackathonwriteservice.team.model.dto;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public record TeamRequest(
        @NotNull
        Long ownerId,
        @NotNull
        Long hackathonId,
        @NotNull
        Set<Long> teamMembersIds,
        @NotNull
        List<TagRequest> tags
) {
}
