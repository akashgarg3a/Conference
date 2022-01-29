package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.services.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

    private SpeakerService speakerService;

    @Autowired
    public SpeakersController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @GetMapping
    public List<Speaker> findAll() {
        return speakerService.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker getById(@PathVariable Long id) {
        return speakerService.getById(id);
    }

    @PostMapping
    public Speaker create(@RequestBody final Speaker speaker) {
        return speakerService.save(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        // We need to add logic for children records before deleting
        speakerService.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
        return speakerService.update(id, speaker);
    }
}
