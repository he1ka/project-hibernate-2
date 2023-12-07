package com.olga_o.repository;

import com.olga_o.entity.Store;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StoreRepository {
    private final SessionFactory sessionFactory;

    public StoreRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Store findById(Short id) {
        try (Session session = sessionFactory.openSession()) {
            Store store = session.get(Store.class, id);

            if (store == null) {
                throw new EntityNotFoundException("Store with ID " + id + " not found");
            }

            return store;
        }
    }
}
