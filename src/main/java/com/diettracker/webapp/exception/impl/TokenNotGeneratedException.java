package com.diettracker.webapp.exception.impl;

import com.diettracker.webapp.enums.ApiErrorCode;
import com.diettracker.webapp.exception.spec.ServiceException;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 10.04.2016.
 */
public class TokenNotGeneratedException extends ServiceException {

    public TokenNotGeneratedException() {
        super(ApiErrorCode.ERR_TOKEN_NOT_GENERATED);
    }
}
