package com.diettracker.webapp.service.spec;

import com.diettracker.webapp.exception.impl.InvalidDateException;
import com.diettracker.webapp.exception.impl.InvalidMealException;
import com.diettracker.webapp.exception.impl.NonExistsingUserMealException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.UserMeal;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 15.7.2015
 */
public interface UserMealService {
    UserMeal addUserMeal(String meal, int userId, String eatingTime) throws ServiceException;

    void deleteUserMeal(int id, int userId) throws UnexpectedErrorException;

    UserMeal getUserMeal(int id, int userId) throws UnexpectedErrorException, NonExistsingUserMealException;
}
