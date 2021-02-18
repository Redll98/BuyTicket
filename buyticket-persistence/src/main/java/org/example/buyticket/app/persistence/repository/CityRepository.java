package org.example.buyticket.app.persistence.repository;

import org.example.buyticket.app.model.entity.geography.City;

import java.util.List;

/**
 * Defines CRUD methods to access City objects in the persistent storage
 *
 * @author Gulyamov Rustam
 */
public interface CityRepository {
    /**
     * Saves specified city instance
     *
     * @param city
     */
    void save(City city);

    /**
     * Returns city with specified identifier. If no city with such identifier
     * then null is returned
     *
     * @param id
     * @return
     */
    City findById(int id);

    /**
     * Delete city with specified identifier
     * @param id
     */
    void delete(int id);

    /**
     * Return all the city
     * @return
     */
    List<City> findAll();
}
