package org.example.buyticket.app.persistence.hibernate;

import org.example.buyticket.app.infra.exception.PersistenceException;
import org.example.buyticket.app.model.entity.geography.Address;
import org.example.buyticket.app.model.entity.geography.City;
import org.example.buyticket.app.model.entity.geography.Coordinate;
import org.example.buyticket.app.model.entity.geography.Station;
import org.example.buyticket.app.model.entity.person.Account;

import org.example.buyticket.app.persistence.hibernate.interceptor.TimestampInterceptor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Component that is responsible for managing
 * Hibernate Session Factory
 *
 * @author Gulyamov Rustam
 */
public class SessionFactoryBuilder {
    private final SessionFactory sessionFactory;

    public SessionFactoryBuilder() {
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(LoadProperties()).build();
        MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(City.class);
        sources.addAnnotatedClass(Coordinate.class);
        sources.addAnnotatedClass(Station.class);
        sources.addAnnotatedClass(Address.class);
        sources.addAnnotatedClass(Account.class);

        org.hibernate.boot.SessionFactoryBuilder factoryBuilder = sources.getMetadataBuilder().build().
                getSessionFactoryBuilder().applyInterceptor(new TimestampInterceptor());

        sessionFactory = factoryBuilder.build();
    }

    private Properties LoadProperties() {
        try {
            InputStream in = SessionFactoryBuilder.class.getClassLoader().getResourceAsStream("application.properties");
            Properties properties = new Properties();

            properties.load(in);

            return properties;
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Returns single instance of session factory
     *
     * @return
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @PreDestroy
    public void destroy() {
        if(sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
