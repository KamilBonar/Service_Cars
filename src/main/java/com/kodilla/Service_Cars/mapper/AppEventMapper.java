package com.kodilla.Service_Cars.mapper;

import com.kodilla.Service_Cars.domain.AppEvent;
import com.kodilla.Service_Cars.domain.AppEventDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppEventMapper {

    public AppEvent mapToAppEvent (AppEventDto appEventDto) {
        return new AppEvent(appEventDto.getId(), appEventDto.getType(), appEventDto.getDate(), appEventDto.getTime(),
                appEventDto.getInfo());
    }

    public AppEventDto mapToAppEventDto (AppEvent appEvent) {
        return new AppEventDto(appEvent.getId(), appEvent.getType(), appEvent.getDate(), appEvent.getTime(),
                appEvent.getInfo());
    }

    public List<AppEvent> mapToAppEventList(List<AppEventDto> appEventDtos) {
        return appEventDtos.stream()
                .map(appEventDto -> new AppEvent(appEventDto.getId(), appEventDto.getType(), appEventDto.getDate(),
                        appEventDto.getTime(),appEventDto.getInfo()))
                .collect(Collectors.toList());
    }

    public List<AppEventDto> mapToAppEventDtoList(List<AppEvent> appEvents) {
        return appEvents.stream()
                .map(appEvent -> new AppEventDto(appEvent.getId(), appEvent.getType(), appEvent.getDate(),
                        appEvent.getTime(),appEvent.getInfo()))
                .collect(Collectors.toList());
    }

}
