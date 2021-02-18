package org.example.buyticket.app.infra.exception.base;

/**
 * Base class for all application-specific exception
 * @author Gulyamov Rustam
 */
public class AppException extends RuntimeException {
    private static final long serialVersionUID = 7837501112802868980L;

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable throwable) { super(throwable); }
}
