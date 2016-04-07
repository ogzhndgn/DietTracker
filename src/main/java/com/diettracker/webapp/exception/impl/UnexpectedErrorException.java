package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.enums.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 4.7.2015
 */
public class UnexpectedErrorException extends ServiceException {

    public UnexpectedErrorException() {
        super(ApiErrorCode.ERR_UNEXPECTED_ERROR);
    }
}
