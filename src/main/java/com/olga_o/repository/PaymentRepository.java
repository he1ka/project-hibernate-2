package com.olga_o.repository;

import com.olga_o.entity.Payment;
import com.olga_o.entity.Rental;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {
    private final SessionFactory sessionFactory;

    public PaymentRepository(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Payment create(Payment payment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(payment);
            transaction.commit();

            return payment;
        }
    }
}
