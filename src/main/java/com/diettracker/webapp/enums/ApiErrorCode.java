package com.diettracker.webapp.enums;

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
    ERR_FOOD_NOT_FOUND,
    ERR_INVALID_MEAL,
    ERR_INVALID_DATE,
    ERR_MEAL_FOOD_CAN_NOT_INSERTED,
    ERR_USER_MEAL_NOT_EXISTS,
    ERR_END_DATE_CAN_NOT_BE_EMPTY,
    ERR_START_DATE_CAN_NOT_BE_EMPTY,
    ERR_SALT_GENERATING,
    ERR_PASSWORD_HASH,
    ERR_INVALID_WEIGHT,
    ERR_TOKEN_NOT_GENERATED,
    ERR_HASH_NOT_FOUND,
    ERR_HASH_NOT_ACTIVE
}
