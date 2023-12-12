package com.olga_o.service;

import com.olga_o.dto.RentalDto;
import com.olga_o.dto.RentalRequestDto;
import com.olga_o.dto.RentalReturnDto;
import com.olga_o.entity.*;
import com.olga_o.mapper.RentalMapper;
import com.olga_o.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final FilmRepository filmRepository;
    private final StoreRepository storeRepository;
    private final StaffRepository staffRepository;
    private final InventoryRepository inventoryRepository;
    private final PaymentService paymentService;
    private final RentalMapper rentalMapper;

    public RentalService(
            @Autowired RentalRepository rentalRepository,
            @Autowired CustomerRepository customerRepository,
            @Autowired FilmRepository filmRepository,
            @Autowired StoreRepository storeRepository,
            @Autowired StaffRepository staffRepository,
            @Autowired InventoryRepository inventoryRepository,
            @Autowired RentalMapper rentalMapper,
            @Autowired PaymentService paymentService
    ) {
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
        this.filmRepository = filmRepository;
        this.storeRepository = storeRepository;
        this.staffRepository = staffRepository;
        this.inventoryRepository = inventoryRepository;
        this.paymentService = paymentService;
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

    public RentalDto createRental(RentalRequestDto rentalRequestDto) {
        Film film = filmRepository.findById(rentalRequestDto.filmId);
        Store store = storeRepository.findById(rentalRequestDto.storeId);
        Staff staff = staffRepository.findById(rentalRequestDto.staffId);
        Customer customer = customerRepository.findById(rentalRequestDto.customerId);

        // Check Film availability in Store Inventory
        Inventory filmInventory = inventoryRepository.findOneByFilmAndStore(film, store);

        // Check Film for Rental availability (not Rented yet, or returned)
        Rental rental = rentalRepository.findByInventory(filmInventory);

        if (rental != null && rental.getReturnDate() == null) {
            throw new RuntimeException("Film inventory is not available for Renting");
        }

        boolean isNewRental = false;

        if (rental == null) {
            rental = new Rental();
            isNewRental = true;
        }

        // Create Rental for Customer
        rental.setInventory(filmInventory);
        rental.setCustomer(customer);
        rental.setStaff(staff);
        rental.setRentalDate(Date.from(Instant.now()));
        rental.setReturnDate(null);

        if (isNewRental) {
            rental = rentalRepository.create(rental);
        } else {
            rental = rentalRepository.update(rental);
        }

        // Create a Payment for a Rental
        paymentService.createPaymentForRental(rental,customer, staff, rentalRequestDto.amount);

        return rentalMapper.map(rental);
    }
}
