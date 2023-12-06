package com.olga_o.mapper;


import com.olga_o.dto.CustomerDto;
import com.olga_o.entity.Customer;
import com.olga_o.repository.AddressRepository;
import com.olga_o.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    private StoreRepository storeRepository;
    private AddressRepository addressRepository;

    public CustomerMapper(
            @Autowired StoreRepository storeRepository,
            @Autowired AddressRepository addressRepository) {
        this.storeRepository = storeRepository;
        this.addressRepository = addressRepository;
    }

    public CustomerDto map(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.id = customer.getCustomerId();
        customerDto.storeId = customer.getStore().getStoreId();
        customerDto.fistName = customer.getFirstName();
        customerDto.lastName = customer.getLastName();
        customerDto.email = customer.getEmail();
        customerDto.addressId = customer.getAddress().getAddressId();

        return customerDto;
    }

    public Customer map(CustomerDto customerDto) {
        Customer customerEntity = new Customer();

        customerEntity.setStore(storeRepository.findById(customerDto.storeId));
        customerEntity.setFirstName(customerDto.fistName);
        customerEntity.setLastName(customerDto.lastName);
        customerEntity.setEmail(customerDto.email);
        customerEntity.setAddress(addressRepository.findById(customerDto.addressId));

        return customerEntity;
    }
}
