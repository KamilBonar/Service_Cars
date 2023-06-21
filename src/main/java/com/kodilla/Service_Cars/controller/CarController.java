package com.kodilla.Service_Cars.controller;

import com.kodilla.Service_Cars.domain.AppEvent;
import com.kodilla.Service_Cars.domain.CarDto;
import com.kodilla.Service_Cars.exception.NotFoundException;
import com.kodilla.Service_Cars.facade.CarFacade;
import com.kodilla.Service_Cars.service.AppEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/Service_Cars")
public class CarController {

    @Autowired
    CarFacade facade;
    @Autowired
    AppEventService appEventService;

    @GetMapping(value = "/cars")
    public List<CarDto> getCars() {
        return facade.fetchAllCars();
    }

    @GetMapping(value = "/cars/{carId}")
    public CarDto getCar(@PathVariable Long carId) throws NotFoundException {
        return facade.fetchCar(carId).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/cars/{carId}")
    public void deleteCar(@PathVariable Long carId) {
        CarDto tempCar = facade.fetchCar(carId).orElse(null);
        if (tempCar != null) {
            appEventService.saveEvent(new AppEvent(AppEvent.EventType.DELETED,
                    "Car (" + tempCar.getPlateNumber() + ") was deleted from database"));
        }
        facade.deleteCar(carId);
    }

    @PutMapping(value = "/cars")
    public CarDto updateCar(@RequestBody CarDto carDto) {
        CarDto tempCarDto = facade.updateCar(carDto);
        if (tempCarDto != null) {
            appEventService.saveEvent(new AppEvent(AppEvent.EventType.UPDATED,
                    "Car (" + tempCarDto.getId() + ") was updated"));
        }
        return tempCarDto;
    }

    @PostMapping(value = "/cars", consumes = APPLICATION_JSON_VALUE)
    public void createCar(@RequestBody CarDto carDto) {
        facade.createCar(carDto);
        appEventService.saveEvent(new AppEvent(AppEvent.EventType.CREATED,
                "Car (" + carDto.getPlateNumber() + ") was created"));
    }
}