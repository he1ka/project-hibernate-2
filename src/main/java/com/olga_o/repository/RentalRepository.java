package com.olga_o.repository;

import com.olga_o.entity.Inventory;
import com.olga_o.entity.Rental;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RentalRepository {
    private final SessionFactory sessionFactory;

    public RentalRepository(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Rental findById(short id) {
        try (Session session = sessionFactory.openSession()) {
            Rental rental = session.get(Rental.class, id);

            if (rental == null) {
                throw new EntityNotFoundException("Rental with ID " + id + " not found");
            }

            return rental;
        }
    }

    public Rental findByInventory(Inventory filmInventory) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Rental r WHERE r.inventory = :inventory";
            Rental rental = session.createQuery(hql, Rental.class)
                    .setParameter("inventory", filmInventory)
                    .setMaxResults(1)
                    .uniqueResult();

            return rental;
        }
    }

    @Transactional
    public Rental update(Rental rental) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(rental);
            transaction.commit();

            return rental;
        }
    }

    @Transactional
    public Rental create(Rental rental) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(rental);
            transaction.commit();

            return rental;
        }
    }

}