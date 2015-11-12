package com.diettracker.webapp.service.spec;

import com.diettracker.webapp.exception.impl.MealFoodCanNotInserted;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.model.Food;
import com.diettracker.webapp.model.MealFood;
import com.diettracker.webapp.model.UserMeal;

import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 20.7.2015
 */
public interface MealFoodService {
    public List<MealFood> insertNewMealFood(UserMeal userMeal, List<Food> foodList) throws UnexpectedErrorException, MealFoodCanNotInserted;

    public void deleteMealFood(int userMealId) throws UnexpectedErrorException;
}
