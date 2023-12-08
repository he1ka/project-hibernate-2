package com.olga_o.repository;

import com.olga_o.entity.Category;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {
    private final SessionFactory sessionFactory;

    public CategoryRepository(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Category getByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Category c WHERE c.name = :name";
            Category category = session.createQuery(hql, Category.class)
                    .setParameter("name", name)
                    .uniqueResult();

            if (category == null) {
                throw new EntityNotFoundException("Category with name " + name + " not found");
            }

            return category;
        }
    }
}
