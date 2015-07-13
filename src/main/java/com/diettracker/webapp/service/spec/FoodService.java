package com.diettracker.webapp.service.spec;

import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.model.Food;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 14.7.2015
 */
public interface FoodService {
    public Food insertNewFood(String name, int userId) throws UnexpectedErrorException;
}
