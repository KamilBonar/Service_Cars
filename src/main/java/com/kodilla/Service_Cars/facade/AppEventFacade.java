package com.kodilla.Service_Cars.facade;

import com.kodilla.Service_Cars.domain.AppEvent;
import com.kodilla.Service_Cars.domain.AppEventDto;
import com.kodilla.Service_Cars.mapper.AppEventMapper;
import com.kodilla.Service_Cars.service.AppEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AppEventFacade {

    @Autowired
    AppEventMapper mapper;
    @Autowired
    AppEventService service;

    public List<AppEventDto> getAllEvents() {
        return mapper.mapToAppEventDtoList(service.getAllEvents());
    }

    public long countEvents() {
        return service.countEvents();
    }

    public List<AppEventDto> findEventByDate(LocalDate date) {
        return mapper.mapToAppEventDtoList(service.findEventByDate(date));
    }

    public List<AppEventDto> findEventByType(AppEvent.EventType type) {
        return mapper.mapToAppEventDtoList(service.findEventByType(type));
    }

    public AppEventDto createApplicationEvent (AppEventDto appEventDto) {
        return mapper.mapToAppEventDto(service.saveEvent(mapper.mapToAppEvent(appEventDto)));
    }

    public void clearEventsList() {
        service.deleteAll();
    }
}
