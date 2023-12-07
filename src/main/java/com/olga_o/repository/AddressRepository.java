package com.olga_o.repository;

import com.olga_o.entity.Address;
import jakarta.persistence.EntityNotFoundException;
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
            Address address = session.get(Address.class, id);

            if (address == null) {
                throw new EntityNotFoundException("Address with ID " + id + " not found");
            }


            return address;


        }
    }
}
