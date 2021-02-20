package org.example.buyticket.app.model.entity.geography;

import org.example.buyticket.app.infra.util.CommonUtil;
import org.example.buyticket.app.model.entity.base.AbstractEntity;
import org.example.buyticket.app.model.entity.transport.TransportType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Any locality that contains transport stations
 *
 * @author Gulyamov Rustam
 */
@Table(name="CITY")
@Entity
public class City extends AbstractEntity {

    public static final String FIELD_NAME = "name";

    private String name;

    /**
     * Name of the district where city is placed
     */
    private String district;

    /**
     * Name of the region where district is located
     * Region is top-level area in the country
     */
    private String region;

    /**
     * A set of transport stations located in this city
     */
    private Set<Station> stations;

    public City() {}

    public City(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min = 2, max = 32)
    @Column(name = "NAME", nullable = false, length=32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min = 2, max = 32)
    @Column(name="DISTRICT", nullable=false, length=32)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @NotNull
    @Size(min = 2, max = 32)
    @Column(name="REGION", nullable = false, length = 32)
    public String getRegion() {
        return region;
    }


    public void setRegion(String region) {
        this.region = region;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city", orphanRemoval = true)
    public Set<Station> getStations() {
        return CommonUtil.getSafeSet(stations);
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }

    /**
     * Add specified station to the city station list
     * @param transportType
     */
    public Station addStation(final TransportType transportType) {
        Objects.requireNonNull(transportType, "station parameter is not initialized");
        if(stations == null) {
            stations = new HashSet<>();
        }
        Station station = new Station(this, transportType);
        stations.add(station);

        return station;
    }

    /**
     * Remove specified station from the city station list
     * @param station
     */
    public void removeStations(Station station) {
        Objects.requireNonNull(station, "station parameter is not initialized");
        if(stations == null) {
            return;
        }
        stations.remove(station);
    }
}
