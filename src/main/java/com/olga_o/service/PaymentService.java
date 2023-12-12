package com.olga_o.service;

import com.olga_o.entity.Customer;
import com.olga_o.entity.Payment;
import com.olga_o.entity.Rental;
import com.olga_o.entity.Staff;
import com.olga_o.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(@Autowired PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPaymentForRental(Rental rental, Customer customer, Staff staff, float amount) {
        Payment payment = new Payment();
        payment.setRental(rental);
        payment.setCustomer(customer);
        payment.setStaff(staff);
        payment.setAmount(BigDecimal.valueOf(amount));
        payment.setPaymentDate(Date.from(Instant.now()));

        return this.paymentRepository.create(payment);
    }
}
