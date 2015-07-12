package com.diettracker.webapp.exception.spec;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 4.7.2015
 */
public class DAOException extends Exception {
    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
