package com.pluralsight.conferencedemo.controllersTest;

import com.pluralsight.conferencedemo.controllers.SessionsController;
import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.services.SessionsService;
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
public class SessionControllerTest {

    @Mock
    private SessionsService sessionsService;
    private SessionsController underTest;

    @BeforeEach
    void setUp() {
        underTest = new SessionsController(sessionsService);
    }

    @Test
    void checkFindAll()  {
        // given
        List<Session> list = new ArrayList();
        // when
        Mockito.when(sessionsService.findAll()).thenReturn(list);
        underTest.findAll();

        // then
        verify(sessionsService, times(1)).findAll();
    }

    @Test
    void checkGetById() {
        // given
        Session session = new Session();
        long id = 1;
        session.setSession_id(id);
        session.setSession_name("Keynote - The Golden Age of Software");
        session.setSession_description("");
        session.setSession_length(45);

        // when
        Mockito.when(sessionsService.getById(id)).thenReturn(session);

        // then
        Session excepted = underTest.getById(id);
        verify(sessionsService, times(1)).getById(id);
        assert(excepted == session);
    }

    @Test
    void checkCreate() {
        // given
        Session session = new Session();
        long id = 1;
        session.setSession_id(id);
        session.setSession_name("Keynote - The Golden Age of Software");
        session.setSession_description("");
        session.setSession_length(45);

        // when
        Mockito.when(sessionsService.save(session)).thenReturn(session);

        // then
        Session excepted = underTest.create(session);
        verify(sessionsService, times(1)).save(session);
        assert(excepted == session);
    }

    @Test
    void checkDelete() {
        // given
        long id = 1;

        // when
        // then
        underTest.delete(id);
        verify(sessionsService, times(1)).deleteById(id);
    }
}
