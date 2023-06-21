package com.kodilla.Service_Cars.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MfApiConfiguration {

    @Value("${mf.api.endpoint}")
    private String mfApiEndpoint;

    @Value("${mf.api.getByNipAddress}")
    private String getByNipAddress;

    @Value("${mf.api.getByRegonAddress}")
    private String getByRegonAddress;
}