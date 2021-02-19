package org.example.buyticket.app.rest.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.example.buyticket.app.model.entity.geography.City;
import org.example.buyticket.app.model.entity.transport.TransportType;
import org.example.buyticket.app.rest.dto.CityDTO;
import org.example.buyticket.app.rest.service.base.BaseResource;
import org.example.buyticket.app.service.GeographicService;
import org.example.buyticket.app.service.transform.Transformer;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/cities")
/**
 * {@link CityResource} is REST web-service that handles city-related requests
 *
 * @author Gulyamov Rustam
 */
public class CityResource extends BaseResource {
    /**
     * Underlying source of data
     */
    private final GeographicService service;

    /**
     * DTO <-> Entity Transformer
     */
    private final Transformer transformer;

    @Inject
    public CityResource(Transformer transformer, GeographicService service) {
        this.transformer = transformer;
        this.service = service;

        City city = new City("St. Petersburg");
        city.addStation(TransportType.AUTO);
        city.setDistrict("St. Petersburg");
        city.setRegion("St. Petersburg");
        service.saveCity(city);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns all the existing cities
     * @returns
     */
    public List<CityDTO> findCities() {
        return service.findCity().stream().map(city -> transformer.transform(city, CityDTO.class))
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    /**
     * Saves new city instance
     * @return
     */
    public void saveCity(CityDTO cityDTO) {
        City city = transformer.unTransform(cityDTO, City.class);

        service.saveCity(city);
    }

    @Path("/{cityId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Find city with specified ID
     */
    public Response findCityByID(@PathParam("cityId") final String cityId) {
        if(!NumberUtils.isNumber(cityId)) {
            return BAD_REQUEST;
        }

        Optional<City> city = service.findCityByID(Integer.parseInt(cityId));

        if(!city.isPresent()) {
            return NOT_FOUND;
        }

        return ok(transformer.transform(city.get(), CityDTO.class));
    }
}
