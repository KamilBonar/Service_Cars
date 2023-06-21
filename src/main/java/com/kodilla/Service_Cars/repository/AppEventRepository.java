package com.kodilla.Service_Cars.repository;

import com.kodilla.Service_Cars.domain.AppEvent;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppEventRepository extends CrudRepository<AppEvent, Long> {

    @Override
    List<AppEvent> findAll();

    @Override
    long count();

    List<AppEvent> findByDate(LocalDate date);
    List<AppEvent> findByType(AppEvent.EventType type);

    AppEvent save(AppEvent event);

    @Override
    void deleteAll();
}
