package org.example.buyticket.app.model.search.criteria;

import org.example.buyticket.app.model.entity.transport.TransportType;

/**
 * Filtering criteria for search stations operation
 * @author Gulyamov Rustam
 */
public class StationCriteria {
    /**
     *City's name
     */
    private String name;

    private TransportType transportType;

    /**
     * Station's address: street, zipCode, building number
     */
    private String address;

    public StationCriteria() {

    }

    public StationCriteria(String name) {
        this.name = name;
    }

    public StationCriteria(TransportType transportType) {
        this.transportType = transportType;
    }

    /**
     * Returns filtering criteria to search station that
     * contains specified name parameter
     * @param name
     * @return
     */
    public static StationCriteria byName(String name) {
        return new StationCriteria(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
