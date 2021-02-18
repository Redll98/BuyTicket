package org.example.buyticket.app.infra.exception.flow;

import org.example.buyticket.app.infra.exception.FlowException;

/**
 * Signals that incorrect parameter was passed to method/constructor
 * @author Gulyamov Rustam
 */
public class InvalidParameterException extends FlowException {

    private static final long serialVersionUID = 4990959228756992926L;

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}