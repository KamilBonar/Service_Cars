package com.kodilla.Service_Cars.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppEventDto {
    private enum EventType {SEND,CREATED,DELETED,UPDATED}

    private long id;
    private AppEvent.EventType type;
    private LocalDate date;
    private LocalTime time;
    private String info;
}