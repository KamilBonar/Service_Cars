package com.kodilla.Service_Cars.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class NbpApiConfiguration {

    @Value("${nbp.api.endpoint}")
    private String nbpApiEndpoint;
}