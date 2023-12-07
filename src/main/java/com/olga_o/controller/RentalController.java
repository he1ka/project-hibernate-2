package com.olga_o.controller;

import com.olga_o.dto.RentalDto;
import com.olga_o.dto.RentalReturnDto;
import com.olga_o.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/rental")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(@Autowired RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/return")
    public RentalDto createCustomer(@RequestBody RentalReturnDto rentalReturnDto) {
        return this.rentalService.returnRental(rentalReturnDto);
    }
}
