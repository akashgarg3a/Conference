package com.pluralsight.conferencedemo.servicesTest;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import com.pluralsight.conferencedemo.services.SpeakerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SpeakerServiceTest {
    @Mock
    private SpeakerRepository speakerRepository;
    private SpeakerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new SpeakerService(speakerRepository);
    }

    @Test
    void checkFindAll() {
        // given
        // when
        underTest.findAll();
        // then
        verify(speakerRepository, times(1)).findAll();
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
        Mockito.when(speakerRepository.getById(id)).thenReturn(speaker);
        Speaker excepted = underTest.getById(id);

        // then
        verify(speakerRepository, times(1)).getById(id);
        assert(speaker == excepted);
    }

    @Test
    void checkSave() {
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
        Mockito.when(speakerRepository.saveAndFlush(speaker)).thenReturn(speaker);
        Speaker excepted = underTest.save(speaker);

        // then
        verify(speakerRepository, times(1)).saveAndFlush(speaker);
        assert(speaker == excepted);
    }

    @Test
    void checkDeleteById() {
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
        underTest.deleteById(id);

        // then
        verify(speakerRepository, times(1)).deleteById(id);
    }

    @Test
    void checkUpdate() {
        // given
        Speaker speaker1 = new Speaker();
        long id = 1;
        speaker1.setSpeaker_id(id);
        speaker1.setSpeaker_bio("Test");
        speaker1.setSpeaker_photo(null);
        speaker1.setCompany("MicroOcean Software");
        speaker1.setFirst_name("Sergio");
        speaker1.setLast_name("Becker");
        speaker1.setTitle("Senior Developer");


        Speaker speaker2 = new Speaker();
        speaker2.setSpeaker_id(id);
        speaker2.setSpeaker_bio("Test");
        speaker2.setSpeaker_photo(null);
        speaker2.setCompany("MicroOcean Software");
        speaker2.setFirst_name("Sergio");
        speaker2.setLast_name("Becker");
        speaker2.setTitle("Senior Developer");

        // when
        Mockito.when(speakerRepository.saveAndFlush(speaker1)).thenReturn(speaker1);
        Mockito.when(speakerRepository.getById(id)).thenReturn(speaker1);

        // then
        Speaker excepted = underTest.update(id, speaker2);

        verify(speakerRepository, times(1)).getById(id);
        verify(speakerRepository, times(1)).saveAndFlush(any(Speaker.class));
        assert(excepted == speaker1);
    }
}
