package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.service.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 4.7.2015
 */
public class UserCanNotCreatedException extends ServiceException {

    public UserCanNotCreatedException() {
        super(ApiErrorCode.ERR_USER_NOT_CREATED);
    }
}
