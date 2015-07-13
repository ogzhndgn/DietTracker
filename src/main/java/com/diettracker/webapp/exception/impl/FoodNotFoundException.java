package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.service.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 14.7.2015
 */
public class FoodNotFoundException extends ServiceException {

    public FoodNotFoundException() {
        super(ApiErrorCode.ERR_FOOD_NOT_FOUND);
    }
}
