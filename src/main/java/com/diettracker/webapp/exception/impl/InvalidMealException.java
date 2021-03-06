package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.enums.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 15.7.2015
 */
public class InvalidMealException extends ServiceException {

    public InvalidMealException() {
        super(ApiErrorCode.ERR_INVALID_MEAL);
    }
}
