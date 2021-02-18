package org.example.buyticket.app.rest.service.base;

import javax.ws.rs.core.Response;

/**
 * Base class for all REST web-services
 *
 * @author Gulyamov Rustam
 */
public abstract class BaseResource {
    /**
     * Shared response that should be returned if request operation
     * returned no data
     */
    protected Response NOT_FOUND;

    /**
     * Returned if client sends request in invalid or unsupported format
     */
    protected Response BAD_REQUEST;

    public BaseResource() {
        NOT_FOUND = Response.status(Response.Status.NOT_FOUND).build();
        BAD_REQUEST = Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * Returns operation result as Response object
     *
     * @param result
     * @return
     */
    protected Response ok(Object result) {
        return Response.ok(result).build();
    }
}
