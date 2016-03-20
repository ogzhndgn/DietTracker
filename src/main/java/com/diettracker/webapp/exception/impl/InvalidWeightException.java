package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.service.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 20.03.2016.
 */
public class InvalidWeightException extends ServiceException {

    public InvalidWeightException() {
        super(ApiErrorCode.ERR_INVALID_WEIGHT);
    }
}
