package com.olga_o.mapper;

import com.olga_o.dto.RentalDto;
import com.olga_o.entity.Rental;
import org.springframework.stereotype.Service;

@Service
public class RentalMapper {
    public RentalDto map(Rental rental) {
        RentalDto rentalDto = new RentalDto();
        rentalDto.rentalId = rental.getRentalId();
        rentalDto.rentalDate = rental.getRentalDate();
        rentalDto.inventoryId = rental.getInventory().getInventoryId();
        rentalDto.customerId = rental.getCustomer().getCustomerId();
        rentalDto.returnDate = rental.getReturnDate();
        rentalDto.staffId = rental.getStaff().getStaffId();

        return rentalDto;
    }
}
