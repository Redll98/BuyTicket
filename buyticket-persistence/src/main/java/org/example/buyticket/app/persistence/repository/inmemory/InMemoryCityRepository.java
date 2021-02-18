package org.example.buyticket.app.persistence.repository.inmemory;

import org.example.buyticket.app.infra.util.CommonUtil;
import org.example.buyticket.app.model.entity.geography.City;
import org.example.buyticket.app.persistence.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCityRepository implements CityRepository {
    /**
     * List of cities
     */
    private final List<City> cities;

    /**
     * increment counter for entity id generation
     */
    private int counter = 0;

    private int stationCounter = 0;

    public InMemoryCityRepository() {
        cities = new ArrayList<>();
    }

    @Override
    public void save(final City city) {
        if(!cities.contains(city)) {
            city.setId(++counter);
            cities.add(city);
        }
        city.getStations().forEach((station -> {
                if(station.getId() == 0) {
                    station.setId(++stationCounter);
                }
            }
        ));
    }

    @Override
    public City findById(final int id) {
        return cities.stream().filter((city -> city.getId() == id)).findFirst().orElse(null);
    }

    @Override
    public void delete(final int id) {

    }

    @Override
    public List<City> findAll() {
        return CommonUtil.getSafeList(cities);
    }
}
