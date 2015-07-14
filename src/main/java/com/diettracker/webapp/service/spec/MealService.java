package com.diettracker.webapp.service.spec;

import com.diettracker.webapp.exception.impl.InvalidMealException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.Meal;

import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 6.7.2015
 */
public interface MealService {
    public List<Meal> getMealList() throws ServiceException;

    public Meal getMealById(int id) throws UnexpectedErrorException, InvalidMealException;
}
