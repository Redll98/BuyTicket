package org.example.buyticket.app.infra.exception;

import org.example.buyticket.app.infra.exception.base.AppException;

/**
 * Signals about data access layer unexpected situations
 * @author Gulyamov Rustam
 */
public class PersistenceException extends AppException {

    private static final long serialVersionUID = -7889716045779735512L;

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(Throwable throwable) {
        super(throwable);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
