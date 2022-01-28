package com.pluralsight.conferencedemo.servicesTest;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import com.pluralsight.conferencedemo.services.SessionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;
    private SessionsService underTest;

    @BeforeEach
    void setUp() {
        underTest = new SessionsService(sessionRepository);
    }

    @Test
    void checkFindAll() {
        // given
        // when
        underTest.findAll();

        // then
        verify(sessionRepository, times(1)).findAll();
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
        Mockito.when(sessionRepository.getById(id)).thenReturn(session);

        // then
        Session excepted = underTest.getById(id);
        verify(sessionRepository, times(1)).getById(id);
        assert(excepted == session);
    }

    @Test
    void checkSave() {
        // given
        Session session = new Session();
        long id = 1;
        session.setSession_id(id);
        session.setSession_name("Keynote - The Golden Age of Software");
        session.setSession_description("");
        session.setSession_length(45);

        // when
        Mockito.when(sessionRepository.saveAndFlush(session)).thenReturn(session);

        // then
        Session excepted = underTest.save(session);
        verify(sessionRepository, times(1)).saveAndFlush(session);
        assert (excepted == session);
    }

    @Test
    void checkDeleteById() {
        // given
        long id = 1;

        // when
        underTest.deleteById(id);

        // then
        verify(sessionRepository, times(1)).deleteById(id);
    }

    @Test
    void checkUpdate() {
        // given
        Session session1 = new Session();
        long id = 1;
        session1.setSession_id(id);
        session1.setSession_name("Keynote - The Golden Age of Software");
        session1.setSession_description("");
        session1.setSession_length(45);

        Session session2 = new Session();
        session2.setSession_id(id);
        session2.setSession_name("Keynote - The Golden Age of Software");
        session2.setSession_description("");
        session2.setSession_length(45);
        // when
        Mockito.when(sessionRepository.saveAndFlush(session1)).thenReturn(session1);
        Mockito.when(sessionRepository.getById(id)).thenReturn(session1);

        // then
        Session excepted = underTest.update(id, session2);

        verify(sessionRepository, times(1)).getById(id);
        verify(sessionRepository, times(1)).saveAndFlush(any(Session.class));
        assert(excepted == session1);
    }
}
