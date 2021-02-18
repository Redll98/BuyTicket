package org.example.buyticket.app.service.impl;

import org.example.buyticket.app.model.entity.geography.City;
import org.example.buyticket.app.model.entity.geography.Station;
import org.example.buyticket.app.model.search.criteria.StationCriteria;
import org.example.buyticket.app.persistence.repository.CityRepository;
import org.example.buyticket.app.service.GeographicService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

public class GeographicServiceImpl implements GeographicService {

    private final CityRepository repository;

    @Inject
    public GeographicServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<City> findCity() {
        return repository.findAll();
    }

    @Override
    public void saveCity(final City city) {
        repository.save(city);
    }

    @Override
    public Optional<City> findCityByID(final int id) {
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public List<Station> searchStation(final StationCriteria criteria) {
        Set<Station> stations = new HashSet<>();

        repository.findAll().stream().forEach(city -> stations.addAll(city.getStations()));

        return stations.stream().filter(station -> station.match(criteria)).collect(Collectors.toList());
    }
}
