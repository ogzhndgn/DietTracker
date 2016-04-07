package com.diettracker.webapp.exception.spec;

import com.diettracker.webapp.enums.ApiErrorCode;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 21.6.2015
 */
public class ServiceException extends Exception {

    protected ServiceException(ApiErrorCode apiErrorCode) {
        super(apiErrorCode.toString());
    }
}
