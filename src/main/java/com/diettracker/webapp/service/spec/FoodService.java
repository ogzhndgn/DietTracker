package com.diettracker.webapp.service.spec;

import com.diettracker.webapp.exception.impl.FoodNotFoundException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.model.Food;

import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 14.7.2015
 */
public interface FoodService {
    Food insertNewFood(String name, int userId) throws UnexpectedErrorException;

    List<Food> getByPrefix(String prefix, int userId) throws FoodNotFoundException, UnexpectedErrorException;

    List<Food> getAllFoodByUser(int userId) throws FoodNotFoundException, UnexpectedErrorException;
}
