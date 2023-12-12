package com.olga_o.repository;

import com.olga_o.entity.Staff;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRepository {
    private final SessionFactory sessionFactory;

    public StaffRepository(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Staff findById(short staffId) {
        try (Session session = sessionFactory.openSession()) {
            Staff staff = session.get(Staff.class, staffId);

            if (staff == null) {
                throw new EntityNotFoundException("Staff with ID " + staffId + " not found");
            }

            return staff;
        }
    }
}
