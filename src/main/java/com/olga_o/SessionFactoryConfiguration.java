package com.olga_o;

import com.olga_o.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SessionFactoryConfiguration {
    private SessionFactory sessionFactory;

    @Bean
    public SessionFactory sessionFactory(){
        if (sessionFactory != null) {
            return sessionFactory;
        }

        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_2");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        // properties.put(Environment.HBM2DDL_AUTO, "update");

        Configuration configuration = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class);

        sessionFactory =  configuration.buildSessionFactory();

        return sessionFactory;
    }
}
