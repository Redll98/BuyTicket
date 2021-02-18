package org.example.buyticket.app.infra.util;

import org.example.buyticket.app.infra.exception.flow.InvalidParameterException;

/**
 * Contains common checks routines
 *
 * @author Gulyamov Rustam
 */
public class Checks {

    private Checks() {
    }

    /**
     * Verifies that specified check passed and throws exception otherwise
     *
     * @param check
     * @param message
     * @throws InvalidParameterException
     */
    public static void checkParameter(boolean check, String message) throws InvalidParameterException {
        if(!check) {
            throw new InvalidParameterException(message);
        }
    }
}
