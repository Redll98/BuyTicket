package org.example.buyticket.app.service;

import org.example.buyticket.app.model.entity.geography.City;
import org.example.buyticket.app.model.entity.geography.Station;
import org.example.buyticket.app.model.search.criteria.StationCriteria;
import org.example.buyticket.app.model.search.criteria.range.RangeCriteria;

import java.util.List;
import java.util.Optional;

/**
 * Entry point to perform operations
 * over geographic entities
 * @author Gulyamov Rustam
 */
public interface GeographicService {
    /**
     * Return list of existing cities
     * @return
     */
    List<City> findCity();

    /**
     * Save specified city instance
     * @param city
     */
    void saveCity(City city);

    /**
     * Returns city with specified id. If no city is found then empty optional is returned
     *
     * @param id
     * @return
     */
    Optional<City> findCityByID(int id);

    /**
     * Returns all the stations that match specified criteria
     *
     * @param criteria
     * @return
     */
    List<Station> searchStation(StationCriteria criteria);
}
