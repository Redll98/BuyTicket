package org.example.buyticket.app.model.entity.geography;

import org.apache.commons.lang3.StringUtils;
import org.example.buyticket.app.model.entity.base.AbstractEntity;
import org.example.buyticket.app.model.entity.transport.TransportType;
import org.example.buyticket.app.model.search.criteria.StationCriteria;

import javax.persistence.*;
import java.util.Objects;

/**
 * Station where passengers can get off or take specific kind
 * of transport. Multiple stations compose route of the trip.
 * @author admin
 *
 */
@Table(name="STATION")
@Entity
public class Station extends AbstractEntity {
    private City city;

    private Address address;

    /**
     * (Optional) Phone of the inquiry office
     */
    private String phone;

    private Coordinate coordinate;

    private TransportType transportType;

    public Station() {}

    public Station(City city, TransportType transportType) {
        this.city = Objects.requireNonNull(city);
        this.transportType = Objects.requireNonNull(transportType);
    }

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name="CITY_ID")
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(name="PHONE", length=16)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Embedded
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Enumerated
    @Column(nullable=false, name="TRANSPORT_TYPE")
    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    /**
     * Verified if current station matches specified criteria
     *
     * @param stationCriteria
     * @return
     */
    public boolean match(final StationCriteria stationCriteria) {
        Objects.requireNonNull(stationCriteria, "station criteria is not initialized");

        if(!StringUtils.isEmpty(stationCriteria.getName())) {
            if(!stationCriteria.getName().equals(city.getName()))
                return false;
        }

        if(stationCriteria.getTransportType() != null) {
            if(!stationCriteria.getTransportType().equals(transportType))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((address != null) ? address.hashCode() : 0);
        result = prime * result + ((city != null) ? city.hashCode() : 0);
        result = prime * result + ((transportType != null) ? transportType.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Station other = (Station) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (transportType != other.transportType)
            return false;
        return true;
    }
}
