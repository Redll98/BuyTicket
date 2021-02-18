package org.example.buyticket.app.model.entity.geography;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Value type that stores address attributes
 * of the specific office or person
 * @author Gulyamov Rustam
 */
@Embeddable
public class Address {
    private String zipCode;

    private String street;

    private String houseNo;

    /**
     * (Optional) Apartment number if it's address
     * of the apartment
     */
    private String apartmentNo;

    @Column(name="ZIP_CODE", length = 10)
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Column(name="STREET", length=32)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name="HOSE_NO", length=16)
    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    @Column(name="APARTMENT_NO", length=16)
    public String getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(String apartmentNo) {
        this.apartmentNo = apartmentNo;
    }
}
