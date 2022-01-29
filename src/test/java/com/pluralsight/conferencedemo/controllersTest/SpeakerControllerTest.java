package com.pluralsight.conferencedemo.controllersTest;

import com.pluralsight.conferencedemo.controllers.SpeakersController;
import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.services.SpeakerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SpeakerControllerTest {

    @Mock
    private SpeakerService speakersService;
    private SpeakersController underTest;

    @BeforeEach
    void setUp() {
        underTest = new SpeakersController(speakersService);
    }

    @Test
    void checkFindAll()  {
        // given
        List<Speaker> list = new ArrayList();
        // when
        Mockito.when(speakersService.findAll()).thenReturn(list);
        underTest.findAll();

        // then
        verify(speakersService, times(1)).findAll();
    }

    @Test
    void checkGetById() {
        // given
        Speaker speaker = new Speaker();
        long id = 1;
        speaker.setSpeaker_id(id);
        speaker.setSpeaker_bio("Test");
        speaker.setSpeaker_photo(null);
        speaker.setCompany("MicroOcean Software");
        speaker.setFirst_name("Sergio");
        speaker.setLast_name("Becker");
        speaker.setTitle("Senior Developer");

        // when
        Mockito.when(speakersService.getById(id)).thenReturn(speaker);

        // then
        Speaker excepted = underTest.getById(id);
        verify(speakersService, times(1)).getById(id);
        assert(excepted == speaker);
    }

    @Test
    void checkCreate() {
        // given
        Speaker speaker = new Speaker();
        long id = 1;
        speaker.setSpeaker_id(id);
        speaker.setSpeaker_bio("Test");
        speaker.setSpeaker_photo(null);
        speaker.setCompany("MicroOcean Software");
        speaker.setFirst_name("Sergio");
        speaker.setLast_name("Becker");
        speaker.setTitle("Senior Developer");

        // when
        Mockito.when(speakersService.save(speaker)).thenReturn(speaker);

        // then
        Speaker excepted = underTest.create(speaker);
        verify(speakersService, times(1)).save(speaker);
        assert(excepted == speaker);
    }

    @Test
    void checkDelete() {
        // given
        long id = 1;

        // when
        // then
        underTest.deleteById(id);
        verify(speakersService, times(1)).deleteById(id);
    }
}
