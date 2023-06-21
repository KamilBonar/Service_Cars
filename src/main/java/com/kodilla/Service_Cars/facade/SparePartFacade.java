package com.kodilla.Service_Cars.facade;

import com.kodilla.Service_Cars.domain.NewPartDto;
import com.kodilla.Service_Cars.mapper.SparePartMapper;
import com.kodilla.Service_Cars.service.SparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SparePartFacade {

    @Autowired
    SparePartService service;
    @Autowired
    SparePartMapper mapper;

    public List<NewPartDto> getAllSPareParts() {
        return mapper.mapToSparePartDtoList(service.getAll());
    }

    public Optional<NewPartDto> getOne(Long sparePartId) {
        return Optional.ofNullable(mapper.mapToSparePartDto(service.getOne(sparePartId).orElseThrow(null)));
    };


    public NewPartDto save(NewPartDto newPartDto) {
        return mapper.mapToSparePartDto(service.save(mapper.mapToSparePart(newPartDto)));
    };

    public void deleteById(Long sparePartId) {
        service.deleteById(sparePartId);
    };

    public long count() {
        return service.count();
    };
}
