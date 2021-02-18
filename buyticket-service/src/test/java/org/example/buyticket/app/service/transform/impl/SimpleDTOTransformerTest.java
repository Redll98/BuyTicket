package org.example.buyticket.app.service.transform.impl;

import org.example.buyticket.app.infra.exception.flow.InvalidParameterException;
import org.example.buyticket.app.model.entity.geography.City;
import org.example.buyticket.app.rest.dto.CityDTO;
import org.example.buyticket.app.service.transform.Transformer;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleDTOTransformerTest {
    private Transformer transformer;

    @Before
    public void setup() {
        transformer = new SimpleDTOTransformer();
    }

    @Test
    public void testTransformCitySuccess() {
        City saintPetersburg = new City("Saint Petersburg");
        saintPetersburg.setId(1);
        saintPetersburg.setRegion("Len");
        saintPetersburg.setDistrict("none");

        CityDTO spbCityDTO = transformer.transform(saintPetersburg, CityDTO.class);
        assertNotNull(spbCityDTO);
        assertEquals(saintPetersburg.getName(), spbCityDTO.getName());
        assertEquals(saintPetersburg.getDistrict(), spbCityDTO.getDistrict());
        assertEquals(saintPetersburg.getRegion(), spbCityDTO.getRegion());
        assertEquals(saintPetersburg.getId(), spbCityDTO.getId());
    }

    @Test(expected = InvalidParameterException.class)
    public void testTransformNullCityFailure() {
        transformer.transform(null, CityDTO.class);
    }

    @Test(expected = InvalidParameterException.class)
    public void testTransformNullDTOClass() {
        transformer.transform(new City("Saint Petersburg"), null);
    }

    @Test
    public void testUnTransformSuccess() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(1);
        cityDTO.setName("Saint Petersburg");
        cityDTO.setDistrict("None");
        cityDTO.setRegion("Len");

        City city = transformer.unTransform(cityDTO, City.class);
        assertNotNull(city);
        assertEquals(city.getName(), cityDTO.getName());
        assertEquals(city.getDistrict(), cityDTO.getDistrict());
        assertEquals(city.getRegion(), cityDTO.getRegion());
        assertEquals(city.getId(), cityDTO.getId());
    }

    @Test(expected = InvalidParameterException.class)
    public void testUnTransformNullEntityClass() {
        transformer.unTransform(new CityDTO(), null);
    }

    @Test(expected = InvalidParameterException.class)
    public void testUnTransformNullDTO() {
        transformer.unTransform(null, City.class);
    }
}
