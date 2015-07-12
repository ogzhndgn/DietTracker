package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.service.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 29.6.2015
 */
public class InvalidEmailException extends ServiceException {

    public InvalidEmailException() {
        super(ApiErrorCode.ERR_INVALID_MAIL_ADDRESS);
    }
}
