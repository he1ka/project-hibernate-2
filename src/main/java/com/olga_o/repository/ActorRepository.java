package com.olga_o.repository;

import com.olga_o.entity.Actor;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ActorRepository {
    private final SessionFactory sessionFactory;

    public ActorRepository(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Actor getByLastName(String lastName) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Actor a WHERE a.lastName = :lastName";
            Actor actor = session.createQuery(hql, Actor.class)
                    .setParameter("lastName", lastName)
                    .uniqueResult();

            if (actor == null) {
                throw new EntityNotFoundException("Actor with last name " + lastName + " not found");
            }

            return actor;
        }
    }    
}
