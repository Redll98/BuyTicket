package org.example.buyticket.app.persistence.repository.hibernate;

import org.apache.commons.lang3.StringUtils;
import org.example.buyticket.app.model.entity.geography.City;
import org.example.buyticket.app.model.entity.geography.Station;
import org.example.buyticket.app.model.search.criteria.StationCriteria;
import org.example.buyticket.app.persistence.hibernate.SessionFactoryBuilder;
import org.example.buyticket.app.persistence.repository.StationRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.util.List;

public class HibernateStationRepository implements StationRepository {

    private final SessionFactory sessionFactory;

    @Inject
    public HibernateStationRepository(SessionFactoryBuilder builder) {
        this.sessionFactory = builder.getSessionFactory();
    }

    @Override
    public List<Station> findAllByCriteria(StationCriteria stationCriteria) {
        try(Session session = sessionFactory.openSession()){
            Criteria criteria = session.createCriteria(stationCriteria.getClass());

            if (stationCriteria.getTransportType() != null) {
                criteria.add(Restrictions.eq(Station.FIELD_CREATED_AT, stationCriteria.getTransportType()));
            }

            if (!StringUtils.isEmpty(stationCriteria.getName())) {
                criteria = criteria.createCriteria(Station.FIELD_CITY);
                criteria.add(Restrictions.eq(City.FIELD_NAME,  stationCriteria.getName()));
            }

            return criteria.list();
        }
    }
}
