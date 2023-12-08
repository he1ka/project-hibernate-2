package com.olga_o.repository;

import com.olga_o.entity.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilmRepository {
    private final SessionFactory sessionFactory;

    public FilmRepository(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Film> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Film", Film.class).list();
        }
    }

    public Film save(Film film) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(film);
            transaction.commit();

            return film;
        }
    }
}
