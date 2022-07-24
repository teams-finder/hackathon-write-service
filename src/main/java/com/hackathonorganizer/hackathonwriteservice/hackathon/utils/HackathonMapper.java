package com.hackathonorganizer.hackathonwriteservice.hackathon.utils;

import com.hackathonorganizer.hackathonwriteservice.hackathon.model.Hackathon;
import com.hackathonorganizer.hackathonwriteservice.hackathon.model.HackathonDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HackathonMapper {

    public static HackathonDto mapHackathonToDto(Hackathon hackathon) {

        return new HackathonDto(hackathon.getId(), hackathon.getName(), hackathon.getDescription());
    }
}
