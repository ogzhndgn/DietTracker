package com.diettracker.webapp.service;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 21.6.2015
 */
public enum ApiErrorCode {

    ERR_USER_NOT_EXISTS,
    ERR_USER_EXISTS,
    ERR_INVALID_MAIL_ADDRESS,
    ERR_INVALID_PASSWORD_FORMAT,
    ERR_PASSWORD_DOESNT_MATCH,
    ERR_UNEXPECTED_ERROR,
    ERR_USER_NOT_CREATED,
    ERR_PASSWORD_CAN_NOT_CHANGED,
    ERR_USER_CAN_NOT_UPDATED,
    ERR_EMPTY_MEAL_LIST,
    ERR_FOOD_NOT_FOUND
}
