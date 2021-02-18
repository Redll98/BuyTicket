package org.example.buyticket.app.infra.exception;

import org.example.buyticket.app.infra.exception.base.AppException;

/**
 * Signals about exceptional cases in the application logic
 * @author Gulyamov Rustam
 */
public class FlowException extends AppException {

    private static final long serialVersionUID = -2889607185988464072L;

    public FlowException(String message) {
        super(message);
    }

    public FlowException(String message, Throwable cause) {
        super(message, cause);
    }
}
