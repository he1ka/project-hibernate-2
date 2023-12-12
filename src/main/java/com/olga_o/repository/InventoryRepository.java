package com.olga_o.repository;

import com.olga_o.entity.Film;
import com.olga_o.entity.Inventory;
import com.olga_o.entity.Staff;
import com.olga_o.entity.Store;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRepository {
    private final SessionFactory sessionFactory;

    public InventoryRepository(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Inventory save(Inventory inventory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(inventory);
            transaction.commit();

            return inventory;
        }
    }

    public Inventory findOneByFilmAndStore(Film film, Store store) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Inventory i WHERE i.film = :film AND i.store = :store";
            Inventory inventory = session.createQuery(hql, Inventory.class)
                    .setParameter("film", film)
                    .setParameter("store", store)
                    .setMaxResults(1)
                    .uniqueResult();

            if (inventory == null) {
                throw new EntityNotFoundException(
                        "Inventory for film ID " + film.getFilmId() + " and Store id " + store.getStoreId()
                );
            }

            return inventory;
        }
    }
}
