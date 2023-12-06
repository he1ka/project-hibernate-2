package com.olga_o.repository;

import com.olga_o.entity.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository {
    private final SessionFactory sessionFactory;

    public AddressRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Address findById(Short id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Address.class, id);
        }
    }
}
