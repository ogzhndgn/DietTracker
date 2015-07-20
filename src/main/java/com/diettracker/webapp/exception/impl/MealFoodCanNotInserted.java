package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.service.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 20.7.2015
 */
public class MealFoodCanNotInserted extends ServiceException {

    public MealFoodCanNotInserted() {
        super(ApiErrorCode.ERR_MEAL_FOOD_CAN_NOT_INSERTED);
    }
}
