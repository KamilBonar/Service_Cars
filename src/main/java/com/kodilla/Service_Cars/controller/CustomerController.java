package com.kodilla.Service_Cars.controller;

import com.kodilla.Service_Cars.domain.AppEvent;
import com.kodilla.Service_Cars.domain.CustomerDto;
import com.kodilla.Service_Cars.exception.NotFoundException;
import com.kodilla.Service_Cars.facade.CustomerFacade;
import com.kodilla.Service_Cars.service.AppEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/Service_Cars")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    CustomerFacade facade;
    @Autowired
    AppEventService appEventService;

    @GetMapping(value="/customers")
    public List<CustomerDto> getUsers() {
        return facade.fetchCustomers();
    }

    @GetMapping(value="/customers/{customerId}")
    public CustomerDto getCustomer(@PathVariable Long customerId) throws NotFoundException {
        return facade.fetchCustomer(customerId).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        CustomerDto tempCustomer = facade.fetchCustomer(customerId).orElse(null);
        if (tempCustomer!=null) {
            appEventService.saveEvent(new AppEvent(AppEvent.EventType.DELETED,
                    "Customer ("+tempCustomer.getLastname()+", "+
                            tempCustomer.getFirstname()+") was deleted from database"));
        }
        facade.deleteCustomer(customerId);
    };

    @PutMapping(value ="/customers")
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto tempCustomer = facade.updateCustomer(customerDto);
        if (tempCustomer!=null) {
            appEventService.saveEvent(new AppEvent(AppEvent.EventType.UPDATED,
                    "Customer (" + tempCustomer.getId() + ") was updated"));
        }
        return tempCustomer;
    }

    @PostMapping(value = "/customers", consumes = APPLICATION_JSON_VALUE)
    public void createCustomer(@RequestBody CustomerDto customerDto) {
        facade.createCustomer(customerDto);
        appEventService.saveEvent(new AppEvent(AppEvent.EventType.CREATED,
                "Customer ("+customerDto.getLastname()+", "+
                        customerDto.getFirstname()+") was created"));
    }
}