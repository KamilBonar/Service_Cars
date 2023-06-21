package com.kodilla.Service_Cars.controller;

import com.kodilla.Service_Cars.domain.AppEvent;
import com.kodilla.Service_Cars.domain.AppEventDto;
import com.kodilla.Service_Cars.facade.AppEventFacade;
import com.kodilla.Service_Cars.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/Service_Cars")
public class AppEventController {

    @Autowired
    AppEventFacade facade;
    @Autowired
    MailService mailService;

    @GetMapping(value = "/events")
    public List<AppEventDto> getAllEvents() {
        return facade.getAllEvents();
    }

    @GetMapping(value = "/events/byType/{type}")
    public List<AppEventDto> getEventsByType(@PathVariable String type) {
        if (validateTypeString(type)) {
            return facade.findEventByType(AppEvent.EventType.valueOf(type.toUpperCase()));
        }
        else return new ArrayList<>();
    }

    @GetMapping(value = "/events/byDate/{date}")
    public List<AppEventDto> getEventsByDate(@PathVariable String date) {
        return facade.findEventByDate(LocalDate.parse(date));
    }

    @PostMapping(value = "/events")
    public AppEventDto createApplicationEvent (@RequestBody AppEventDto appEventDto) {
        AppEventDto returnedDto = facade.createApplicationEvent(appEventDto);
        return returnedDto;
    }

    private boolean validateTypeString (String type) {
        boolean isCorrectString = false;
        for (AppEvent.EventType enumType:AppEvent.EventType.values()) {
            if (type.equalsIgnoreCase(enumType.toString())) {
                isCorrectString = true;
            }
        }
        return isCorrectString;
    }
}