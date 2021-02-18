package org.example.buyticket.app.infra.exception;

import org.example.buyticket.app.infra.exception.base.AppException;

/**
 * Signals about exception causes in the work of external services and API
 * @author Gulyamov Rustam
 */
public class CommunicationException extends AppException {

    private static final long serialVersionUID = -2850898723336164866L;

    public CommunicationException(String message) {
        super(message);
    }

    public CommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
