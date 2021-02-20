package org.example.buyticket.app.persistence.repository;

import org.example.buyticket.app.model.entity.geography.Station;
import org.example.buyticket.app.model.search.criteria.StationCriteria;

import java.util.List;

/**
 * Defines CRUD methods to access Station objects in the persistence storage
 *
 * @author Gulyamov Rustam
 */
public interface StationRepository {
    /**
     * Returns all the stations that match specified criteria
     *
     * @param stationCriteria
     * @return
     */
    List<Station> findAllByCriteria(StationCriteria stationCriteria);
}
