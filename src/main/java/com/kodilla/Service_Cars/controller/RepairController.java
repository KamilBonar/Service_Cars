package com.kodilla.Service_Cars.controller;

import com.kodilla.Service_Cars.domain.AppEvent;
import com.kodilla.Service_Cars.domain.RepairDto;
import com.kodilla.Service_Cars.exception.NotFoundException;
import com.kodilla.Service_Cars.facade.RepairFacade;
import com.kodilla.Service_Cars.service.AppEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/Service_Cars")
public class RepairController {

    @Autowired
    RepairFacade facade;

    @Autowired
    AppEventService appEventService;

    @GetMapping(value="/repairs")
    public List<RepairDto> getRepairs() {
        return facade.fetchAllRepairs();
    }

    @GetMapping(value="/repairs/{repairId}")
    public RepairDto getRepair(@PathVariable Long repairId) throws NotFoundException {
        return facade.fetchRepair(repairId).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/repairs/{repairId}")
    public void deleteRepair(@PathVariable Long repairId){
        RepairDto tempRepair = facade.fetchRepair(repairId).orElse(null);
        if (tempRepair!=null) {
            appEventService.saveEvent(new AppEvent(AppEvent.EventType.DELETED,
                    "Repair ("+tempRepair.getId()+") was deleted from database"));
        }
        facade.deleteRepair(repairId);
    }

    @PutMapping(value = "/repairs")
    public RepairDto updateRepair(@RequestBody RepairDto repairDto) {
        RepairDto tempRepairDto = facade.updateRepair(repairDto);
        if (tempRepairDto!=null) {
            appEventService.saveEvent(new AppEvent(AppEvent.EventType.UPDATED,
                    "Repair (" + tempRepairDto.getId() + ") was updated"));
        }
        return tempRepairDto;
    }

    @PostMapping(value = "/repairs", consumes = APPLICATION_JSON_VALUE)
    public void createRepair(@RequestBody RepairDto repairDto) {
        facade.createRepair(repairDto);
        appEventService.saveEvent(new AppEvent(AppEvent.EventType.CREATED,
                "Repair ("+repairDto.getId()+") was created"));
    }
}