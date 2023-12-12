package com.olga_o.repository;

import com.olga_o.entity.Customer;
import com.olga_o.entity.Film;
import com.olga_o.entity.Rental;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {
    private final SessionFactory sessionFactory;

    public CustomerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Customer> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Customer", Customer.class).list();
        }
    }

    public Customer save(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();

            return customer;
        }
    }

    public Customer findById(short customerId) {
        try (Session session = sessionFactory.openSession()) {
            Customer customer = session.get(Customer.class, customerId);

            if (customer == null) {
                throw new EntityNotFoundException("Customer with ID " + customerId + " not found");
            }

            return customer;
        }
    }
}
