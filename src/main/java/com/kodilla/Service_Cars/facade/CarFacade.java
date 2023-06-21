package com.kodilla.Service_Cars.facade;

import com.kodilla.Service_Cars.domain.CarDto;
import com.kodilla.Service_Cars.mapper.CarMapper;
import com.kodilla.Service_Cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class CarFacade {

    @Autowired
    CarService service;
    @Autowired
    CarMapper mapper;

    public List<CarDto> fetchAllCars() {
        return mapper.mapToCarDtoList(service.getCars());
    }

    public Optional<CarDto> fetchCar(Long carId) {
        return Optional.ofNullable(mapper.mapToCarDto(service.getCar(carId).orElse(null)));
    }

    public void deleteCar(Long carId){
        service.deleteCar(carId);
    }

    public CarDto updateCar(CarDto carDto) {
        return mapper.mapToCarDto(service.save(mapper.mapToCar(carDto)));
    }

    public void createCar(CarDto carDto) {
        service.save(mapper.mapToCar(carDto));
    }
}
