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
        return tagRepository.findById(id)
                .map(tagToEdit -> {
                    tagToEdit.setName(tagRequest.name());
                    return tagRepository.save(Tag.builder()
                            .name(tagRequest.name())
                            .build());
                }).orElseThrow(() -> {
                    log.error(String.format("Tag id = %d not found", id));
                    return new ResourceNotFoundException(String.format("Tag id = %d not found", id));
                });
    }

    public void deleteById(Long id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(String.format("Tag id = %d not found", id));
        }

    }


    protected boolean existsByName(String name) {
        if (tagRepository.existsByNameIgnoreCase(name)) {
            log.error("Tag name {} already exists", name);
            throw new ResourceAlreadyExistsException(String.format("Tag name %s already exists", name));
        }
        return false;
    }

    protected Tag findByName(String name) {
        return tagRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> {
                    log.error(String.format("Tag name %s not found", name));
                    throw new ResourceAlreadyExistsException(String.format("Tag name %s not found", name));
                });
    }


}
