package com.olga_o.repository;

import com.olga_o.entity.Store;
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
            return session.get(Store.class, id);
        }
    }
}
