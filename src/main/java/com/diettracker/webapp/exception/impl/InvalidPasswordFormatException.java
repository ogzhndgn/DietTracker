package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.enums.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 29.6.2015
 */
public class InvalidPasswordFormatException extends ServiceException {

    public InvalidPasswordFormatException() {
        super(ApiErrorCode.ERR_INVALID_PASSWORD_FORMAT);
    }
}
