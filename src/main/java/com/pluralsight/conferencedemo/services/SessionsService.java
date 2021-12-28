package com.pluralsight.conferencedemo.services;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionsService {
    private SessionRepository sessionRepository;

    @Autowired
    public SessionsService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public Session getById(Long id) {
        return sessionRepository.getById(id);
    }

    public Session save(Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    public void deleteById(Long id) {
        // We need to add logic for children records before deleting
        sessionRepository.deleteById(id);
    }

    public Session update(Long id, Session session) {
        // As it update we except all the value that are passed
        // TODO: Add validation that all the parameters are passed, except return 400 bad payload

        Session existingSession = sessionRepository.getById(id);
        // used for copy the data
        BeanUtils.copyProperties(session, existingSession,"session_id");
        return save(existingSession);
    }
}
