package com.kodilla.Service_Cars.service;

import com.kodilla.Service_Cars.domain.AppEvent;
import com.kodilla.Service_Cars.repository.AppEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppEventService {

    @Autowired
    AppEventRepository repository;

    public List<AppEvent> getAllEvents() {
        return repository.findAll();
    }

    public long countEvents() {
        return repository.count();
    }

    public List<AppEvent> findEventByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public List<AppEvent> findEventByType(AppEvent.EventType type) {
        return repository.findByType(type);
    }

    public AppEvent saveEvent(AppEvent appEvent) {
        return repository.save(appEvent);
    }

    public void deleteAll () {
        repository.deleteAll();
    }

    public long count() {
        return repository.count();
    }
}
