package com.olga_o.repository;

import com.olga_o.entity.Customer;
import com.olga_o.entity.Film;
import com.olga_o.entity.Language;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LanguageRepository {
    private final SessionFactory sessionFactory;

    public LanguageRepository(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Language getByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Language l WHERE l.name = :name";
            Language language = session.createQuery(hql, Language.class)
                    .setParameter("name", name)
                    .uniqueResult();

            if (language == null) {
                throw new EntityNotFoundException("Language with name " + name + " not found");
            }

            return language;
        }
    }
}
