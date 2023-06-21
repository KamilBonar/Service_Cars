package com.kodilla.Service_Cars.mapper;

import com.kodilla.Service_Cars.domain.SparePart;
import com.kodilla.Service_Cars.domain.NewPartDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SparePartMapper {

    public SparePart mapToSparePart(final NewPartDto newPartDto) {
        return new SparePart(newPartDto.getId(), newPartDto.getCarBrand(), newPartDto.getModel(),
                newPartDto.getManufacturer(), newPartDto.getPrice());
    }

    public NewPartDto mapToSparePartDto(final SparePart sparePart) {
        return new NewPartDto(sparePart.getId(), sparePart.getCarBrand(), sparePart.getModel(), sparePart.getManufacturer(),
                sparePart.getPrice());
    }

    public List<SparePart> mapToSparePartList(final List<NewPartDto> newPartDtos) {
        return newPartDtos.stream()
                .map(newPartDto -> new SparePart(newPartDto.getId(), newPartDto.getCarBrand(), newPartDto.getModel(),
                        newPartDto.getManufacturer(), newPartDto.getPrice()))
                .collect(Collectors.toList());
    }

    public List<NewPartDto> mapToSparePartDtoList(final List<SparePart> spareParts) {
        return spareParts.stream()
                .map(sparePartDto -> new NewPartDto(sparePartDto.getId(), sparePartDto.getCarBrand(), sparePartDto.getModel(),
                        sparePartDto.getManufacturer(), sparePartDto.getPrice()))
                .collect(Collectors.toList());
    }
}
