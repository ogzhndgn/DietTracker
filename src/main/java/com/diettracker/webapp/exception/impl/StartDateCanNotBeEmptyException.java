package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.service.ApiErrorCode;

/**
 * Created by Oguzhan on 13.12.2015.
 */
public class StartDateCanNotBeEmptyException extends ServiceException {
    public StartDateCanNotBeEmptyException() {
        super(ApiErrorCode.ERR_START_DATE_CAN_NOT_BE_EMPTY);
    }
}
