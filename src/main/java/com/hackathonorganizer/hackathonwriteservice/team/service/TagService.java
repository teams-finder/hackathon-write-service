package com.hackathonorganizer.hackathonwriteservice.team.service;

import com.hackathonorganizer.hackathonwriteservice.team.exception.ResourceAlreadyExistsException;
import com.hackathonorganizer.hackathonwriteservice.team.exception.ResourceNotFoundException;
import com.hackathonorganizer.hackathonwriteservice.team.model.Tag;
import com.hackathonorganizer.hackathonwriteservice.team.model.dto.TagRequest;
import com.hackathonorganizer.hackathonwriteservice.team.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagService {

    private final TagRepository tagRepository;

    public Tag create(TagRequest tagRequest) {
        existsByName(tagRequest.name());
        val tagToSave = Tag.builder()
                .name(tagRequest.name())
                .build();
        return tagRepository.save(tagToSave);
    }

    public Tag editById(Long id, TagRequest tagRequest) {
        existsByName(tagRequest.name());
        val tagToSave = tagRepository.findById(id).orElseThrow(() -> {
            log.error(String.format("Tag id = %d not found", id));
            throw new ResourceNotFoundException(String.format("Tag id = %d not found", id));
        });
        tagToSave.setName(tagRequest.name());
        return tagRepository.save(tagToSave);
    }

    public void deleteById(Long id) {
        if(tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(String.format("Tag id = %d not found", id));
        }

    }


    private boolean existsByName(String name) {
        if (tagRepository.existsByNameIgnoreCase(name)) {
            log.error("Tag name {} already exists", name);
            throw new ResourceAlreadyExistsException(String.format("Tag name %s already exists", name));
        }
        return false;
    }


}
