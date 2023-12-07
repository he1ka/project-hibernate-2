package com.olga_o.controller;

import com.olga_o.dto.CustomerDto;
import com.olga_o.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(@Autowired CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }
}
