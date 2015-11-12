package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.service.ApiErrorCode;

/**
 * Created by the Poet on 5.11.2015.
 */
public class NonExistsingUserMealException extends ServiceException {

    public NonExistsingUserMealException() {
        super(ApiErrorCode.ERR_USER_MEAL_NOT_EXISTS);
    }
}
