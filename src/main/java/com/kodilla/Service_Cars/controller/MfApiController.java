package com.kodilla.Service_Cars.controller;

import com.kodilla.Service_Cars.webService.MfApiClient;
import com.kodilla.Service_Cars.webService.MfApiResponseDto;
import com.kodilla.Service_Cars.webService.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/Service_Cars")
@CrossOrigin("*")
public class MfApiController {

    @Autowired
    MfApiClient client;

    @GetMapping(value = "/getCustomerNip/{requestNip}")
    public SubjectDto getCustomerInfoByNip(@PathVariable String requestNip) {
        MfApiResponseDto response = client.getCustomerInfoFromMFByNip(requestNip);
        return response.getResult().getSubject();
    }

    @GetMapping(value = "/getCustomerRegon/{requestRegon}")
    public SubjectDto getCustomerInfoByRegon(@PathVariable String requestRegon) {
        MfApiResponseDto response = client.getCustomerInfoFromMFByRegon(requestRegon);
        return response.getResult().getSubject();
    }

    @GetMapping(value = "/checkIsCustomerActive/{requestNip}")
    public boolean checkIfCustomerIsActive(@PathVariable String requestNip) {
        return client.getCustomerActivityStatus(requestNip);
    }
}
