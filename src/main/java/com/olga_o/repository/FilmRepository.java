package com.olga_o.repository;

import com.olga_o.entity.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
