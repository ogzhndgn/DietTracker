package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.service.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 6.7.2015
 */
public class EmptyMealListException extends ServiceException {

    public EmptyMealListException() {
        super(ApiErrorCode.ERR_EMPTY_MEAL_LIST);
    }
}
