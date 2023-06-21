package com.kodilla.Service_Cars.controller;

import com.kodilla.Service_Cars.domain.AppEvent;
import com.kodilla.Service_Cars.domain.NewPartDto;
import com.kodilla.Service_Cars.exception.NotFoundException;
import com.kodilla.Service_Cars.facade.SparePartFacade;
import com.kodilla.Service_Cars.service.AppEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/Service_Cars")
public class SparePartController {

    @Autowired
    SparePartFacade facade;

    @Autowired
    AppEventService appEventService;

    @GetMapping(value = "/spares")
    public List<NewPartDto> getSpareParts() {
        return facade.getAllSPareParts();
    }

    @GetMapping(value = "/spares/{sparePartId}")
    public NewPartDto getSparePart(@PathVariable Long sparePartId) throws NotFoundException {
        return facade.getOne(sparePartId).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/spares/{sparePartId}")
    public void deleteSparePart(@PathVariable Long sparePartId) {
        NewPartDto newPartDto = facade.getOne(sparePartId).orElse(null);
        if (newPartDto != null) {
            appEventService.saveEvent(new AppEvent(AppEvent.EventType.DELETED,
                    "Spare (" + newPartDto.getModel() + ") was deleted from database"));
        }
        facade.deleteById(sparePartId);
    }

    @PostMapping(value = "/spares", consumes = APPLICATION_JSON_VALUE)
    public void createSparePart(@RequestBody NewPartDto newPartDto) {
        facade.save(newPartDto);
        appEventService.saveEvent(new AppEvent(AppEvent.EventType.CREATED,
                "Spare (" + newPartDto.getModel() + " " + newPartDto.getManufacturer() + ") was created"));
    }

    @PutMapping(value = "/spares", consumes = APPLICATION_JSON_VALUE)
    public void updateSparePart(@RequestBody NewPartDto newPartDto) {
        facade.save(newPartDto);
        appEventService.saveEvent(new AppEvent(AppEvent.EventType.UPDATED,
                "Spare (" + newPartDto.getModel() + " " + newPartDto.getManufacturer() + ") was updated"));
    }


}