package org.example.buyticket.app.config;

import org.example.buyticket.app.persistence.hibernate.SessionFactoryBuilder;
import org.example.buyticket.app.persistence.repository.CityRepository;
import org.example.buyticket.app.persistence.repository.hibernate.HibernateCityRepository;
import org.example.buyticket.app.service.GeographicService;
import org.example.buyticket.app.service.impl.GeographicServiceImpl;
import org.example.buyticket.app.service.transform.Transformer;
import org.example.buyticket.app.service.transform.impl.SimpleDTOTransformer;


import javax.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Binds bean implementations and implemented interfaces
 *
 * @author Gulyamov Rustam
 */
public class ComponentBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(HibernateCityRepository.class).to(CityRepository.class).in(Singleton.class);
        bind(SimpleDTOTransformer.class).to(Transformer.class).in(Singleton.class);
        bind(GeographicServiceImpl.class).to(GeographicService.class).in(Singleton.class);
        bind(SessionFactoryBuilder.class).to(SessionFactoryBuilder.class).in(Singleton.class);
    }
}
