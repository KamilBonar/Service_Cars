package com.kodilla.Service_Cars.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewPartDto {
    private long id;
    private Car.CarBrand carBrand;
    private String model;
    private String manufacturer;
    private double price;


}