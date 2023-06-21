package com.kodilla.Service_Cars.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class MailConfiguration {

    @Value("${spring.mail.username}")
    private String adminMailAddress;

    @Value("${spring.mail.password}")
    private String adminMailPassword;
}