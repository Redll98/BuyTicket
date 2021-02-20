package org.example.buyticket.app.persistence.repository.hibernate;

import org.example.buyticket.app.model.entity.geography.City;
import org.example.buyticket.app.persistence.repository.CityRepository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.List;

public class HibernateCityRepository implements CityRepository {
    SessionFactory sessionFactory;

    @Inject
    public HibernateCityRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(City city) {
        try(Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(city);
        }
    }

    @Override
    public City findById(int id) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(City.class, id);
        }
    }

    @Override
    public void delete(int id) {
        try(Session session = sessionFactory.openSession()) {
            City city = session.get(City.class, id);
            if(city != null) {
                session.delete(city);
            }
        }
    }

    @Override
    public List<City> findAll() {
        try(Session session = sessionFactory.openSession()) {
            return session.createCriteria(City.class).list();
        }
    }
}
