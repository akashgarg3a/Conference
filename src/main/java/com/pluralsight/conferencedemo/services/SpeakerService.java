package com.pluralsight.conferencedemo.services;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakerService {
    private SpeakerRepository speakerRepository;

    @Autowired
    public SpeakerService(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    public Speaker getById(Long id) {
        return speakerRepository.getById(id);
    }

    public Speaker save(Speaker speaker) {
        return speakerRepository.saveAndFlush(speaker);
    }

    public void deleteById(Long id) {
        speakerRepository.deleteById(id);
    }

    public Speaker update(Long id, Speaker speaker) {
        // As it update we except all the value that are passed
        // TODO: Add validation that all the parameters are passed, except return 400 bad payload

        Speaker existingSpeaker = speakerRepository.getById(id);
        // used for copy the data
        BeanUtils.copyProperties(speaker, existingSpeaker,"speaker_id");
        return save(existingSpeaker);
    }
}
