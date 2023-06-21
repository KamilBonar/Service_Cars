package com.kodilla.Service_Cars.controller;

import com.kodilla.Service_Cars.webService.NbpApiClient;
import com.kodilla.Service_Cars.webService.NbpApiResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/Service_Cars")
public class NbpApiController {

    @Autowired
    NbpApiClient client;

    @GetMapping(value = "/currency/{code}")
    public double getFactor(@PathVariable String code) {
        NbpApiResponseDto nbpApiResponseDto = client.getCurrentCurrencyFactor(code);
        return Double.valueOf(nbpApiResponseDto.getRates()[0].getMid());
    }
}
