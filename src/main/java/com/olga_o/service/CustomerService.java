package com.olga_o.service;

import com.olga_o.dto.CustomerDto;
import com.olga_o.entity.Customer;
import com.olga_o.mapper.CustomerMapper;
import com.olga_o.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerService(
            @Autowired CustomerRepository customerRepository,
            @Autowired CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.map(customerDto);
        customer.setActive(true);

        customer = this.customerRepository.save(customer);

        return customerMapper.map(customer);
    }
}
