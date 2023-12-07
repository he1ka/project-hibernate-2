package com.olga_o.service;

import com.olga_o.dto.RentalDto;
import com.olga_o.dto.RentalReturnDto;
import com.olga_o.entity.Customer;
import com.olga_o.entity.Rental;
import com.olga_o.mapper.RentalMapper;
import com.olga_o.repository.CustomerRepository;
import com.olga_o.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final RentalMapper rentalMapper;

    public RentalService(
            @Autowired RentalRepository rentalRepository,
            @Autowired CustomerRepository customerRepository,
            @Autowired RentalMapper rentalMapper
    ) {
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
        this.rentalMapper = rentalMapper;
    }

    public RentalDto returnRental(RentalReturnDto rentalReturnDto) {
        Rental rental = rentalRepository.findById(rentalReturnDto.rentalId);
        Customer customer = customerRepository.findById(rentalReturnDto.customerId);

        return returnRental(rental, customer);
    }

    public RentalDto returnRental(Rental rental, Customer customer) {
        rental.setReturnDate(Date.from(Instant.now()));

        return rentalMapper.map(this.rentalRepository.update(rental));
    }
}
