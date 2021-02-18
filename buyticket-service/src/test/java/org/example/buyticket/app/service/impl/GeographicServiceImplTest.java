package org.example.buyticket.app.service.impl;

import org.example.buyticket.app.model.entity.geography.City;
import org.example.buyticket.app.model.entity.geography.Station;
import org.example.buyticket.app.model.entity.transport.TransportType;
import org.example.buyticket.app.model.search.criteria.StationCriteria;
import org.example.buyticket.app.persistence.hibernate.SessionFactoryBuilder;
import org.example.buyticket.app.persistence.repository.hibernate.HibernateCityRepository;
import org.example.buyticket.app.persistence.repository.inmemory.InMemoryCityRepository;
import org.example.buyticket.app.service.GeographicService;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

/**
 * Contains unit tests for {@link GeographicServiceImpl}
 *
 * @author Gulyamov Rustam
 */
public class GeographicServiceImplTest {
    private static final int DEFAULT_CITY_ID = 1;

    private GeographicService geographicService;

    @Before
    public void setup() {
        geographicService = new GeographicServiceImpl(new InMemoryCityRepository());
    }

    @Test
    public void testNoDataReturnedAtStart() {
        List<City> cities = geographicService.findCity();
        assertTrue(cities.isEmpty());
    }

    @Test
    public void testSaveNewCitySuccess() {
        City newCity = new City("St. Petersburg");
        geographicService.saveCity(newCity);

        List<City> cities = geographicService.findCity();
        assertEquals(cities.size(), 1);
        assertEquals(cities.get(0).getName(), "St. Petersburg");
    }

    @Test
    public void findCityByIDSuccess() {
        City city = new City("St. Petersburg");
        geographicService.saveCity(city);

        Optional<City> foundCity = geographicService.findCityByID(DEFAULT_CITY_ID);
        assertTrue(foundCity.isPresent());
        assertEquals(foundCity.get().getId(), DEFAULT_CITY_ID);
    }

    @Test
    public void findCityByIDNotFound() {
        Optional<City> foundCity = geographicService.findCityByID(DEFAULT_CITY_ID);
        assertFalse(foundCity.isPresent());
    }

    @Test
    public void testSearchStationsByNameSuccess() {
        City city = new City("St. Petersburg");
        city.setId(DEFAULT_CITY_ID);
        city.addStation(TransportType.AVIA);
        city.addStation(TransportType.AUTO);
        city.addStation(TransportType.RAILWAY);
        geographicService.saveCity(city);

        StationCriteria stationCriteria = new StationCriteria();
        stationCriteria.setName("St. Petersburg");
        List<Station> stationList = geographicService.searchStation(stationCriteria);

        assertNotNull(stationList);
        assertEquals(stationList.size(), 3);
        assertEquals(stationList.get(0).getCity(), city);
    }

    @Test
    public void testSearchStationsByNameNotFound() {
        List<Station> stationList = geographicService.searchStation(
                StationCriteria.byName("St.Petersburg"));

        assertTrue(stationList.isEmpty());
    }

    @Test
    public void testSearchStationByTransportTypeSuccess() {
        City city1 = new City("St. Petersburg");
        city1.addStation(TransportType.AUTO);
        geographicService.saveCity(city1);
        City city2 = new City("Moscow");
        city2.addStation(TransportType.AUTO);
        geographicService.saveCity(city2);

        StationCriteria stationCriteria = new StationCriteria();
        stationCriteria.setTransportType(TransportType.AUTO);
        List<Station> stationList = geographicService.searchStation(stationCriteria);

        assertNotNull(stationList);
        assertEquals(stationList.size(), 2);
    }

    @Test
    public void testSearchStationByTransportTypeNotFound() {
        City city1 = new City("St. Petersburg");
        city1.addStation(TransportType.AUTO);
        City city2 = new City("Chelyabinsk");
        city2.addStation(TransportType.RAILWAY);
        geographicService.saveCity(city1);
        geographicService.saveCity(city2);

        List<Station> stationList = geographicService.searchStation(new StationCriteria(TransportType.AVIA));

        assertNotNull(stationList);
        assertTrue(stationList.isEmpty());
    }
}
