package org.example.buyticket.app.service.impl;

import org.example.buyticket.app.model.entity.geography.City;
import org.example.buyticket.app.model.entity.geography.Station;
import org.example.buyticket.app.model.entity.transport.TransportType;
import org.example.buyticket.app.model.search.criteria.StationCriteria;
import org.example.buyticket.app.persistence.hibernate.SessionFactoryBuilder;
import org.example.buyticket.app.persistence.repository.CityRepository;
import org.example.buyticket.app.persistence.repository.hibernate.HibernateCityRepository;
import org.example.buyticket.app.persistence.repository.inmemory.InMemoryCityRepository;
import org.example.buyticket.app.service.GeographicService;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Contains unit tests for {@link GeographicServiceImpl}
 *
 * @author Gulyamov Rustam
 */
public class GeographicServiceImplTest {
    private static final int DEFAULT_CITY_ID = 1;

    private static GeographicService geographicService;

    private static ExecutorService service;

    @BeforeClass
    public static void setup() {
        SessionFactoryBuilder factoryBuilder = new SessionFactoryBuilder();
        CityRepository cityRepository = new HibernateCityRepository(factoryBuilder.getSessionFactory());
        geographicService = new GeographicServiceImpl(cityRepository);

        service = Executors.newCachedThreadPool();
    }

    @AfterClass
    public static void tearDown() {
        service.shutdownNow();
    }

    @Test
    public void testNoDataReturnedAtStart() {
        List<City> cities = geographicService.findCity();
        assertTrue(cities.isEmpty());
    }

    @Test
    public void testSaveNewCitySuccess() {
        int cityCount = geographicService.findCity().size();

        City newCity = createCity();
        geographicService.saveCity(newCity);

        List<City> cities = geographicService.findCity();
        assertEquals(cities.size(), cityCount + 1);
        assertEquals(cities.get(0).getName(), "St. Petersburg");
    }

    @Test
    public void findCityByIDSuccess() {
        City city = createCity();
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
        City city = new City("Chelyabinsk");
        city.setDistrict("Chelyabinsk");
        city.setRegion("Chelyabinsk");
        city.setId(DEFAULT_CITY_ID);
        city.addStation(TransportType.AVIA);
        city.addStation(TransportType.AUTO);
        city.addStation(TransportType.RAILWAY);
        geographicService.saveCity(city);

        StationCriteria stationCriteria = StationCriteria.byName("Chelyabinsk");
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
        int stationCount = geographicService.searchStation(new StationCriteria(TransportType.AUTO)).size();

        City city1 = createCity();
        city1.addStation(TransportType.AUTO);
        geographicService.saveCity(city1);
        City city2 = new City("Moscow");
        city2.addStation(TransportType.AUTO);
        geographicService.saveCity(city2);

        List<Station> stations = geographicService.searchStation(new StationCriteria(TransportType.AUTO));

        assertNotNull(stations);
        assertEquals(stations.size(), stationCount + 2);
    }

    @Test
    public void testSearchStationByTransportTypeNotFound() {
        City city1 = createCity();
        city1.addStation(TransportType.AUTO);
        City city2 = new City("Chelyabinsk");
        city2.addStation(TransportType.RAILWAY);
        geographicService.saveCity(city1);
        geographicService.saveCity(city2);

        List<Station> stationList = geographicService.searchStation(new StationCriteria(TransportType.AVIA));

        assertNotNull(stationList);
        assertTrue(stationList.isEmpty());
    }

    private City createCity() {
        City city = new City("St. Petersburg");
        city.setDistrict("St. Petersburg");
        city.setRegion("St. Petersburg");

        return city;
    }
}
