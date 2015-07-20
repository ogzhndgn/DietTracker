package com.diettracker.webapp.service.impl;

import com.diettracker.webapp.dao.MealFoodDao;
import com.diettracker.webapp.exception.impl.MealFoodCanNotInserted;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.Food;
import com.diettracker.webapp.model.MealFood;
import com.diettracker.webapp.model.UserMeal;
import com.diettracker.webapp.service.spec.MealFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 20.7.2015
 */
@Service
public class MealFoodServiceImpl implements MealFoodService {

    @Autowired
    MealFoodDao mealFoodDao;

    @Override
    public List<MealFood> insertNewMealFood(UserMeal userMeal, List<Food> foodList) throws UnexpectedErrorException, MealFoodCanNotInserted {
        int userMealId = userMeal.getId();
        List<MealFood> mealFoodList = new ArrayList<>();
        this.insert(foodList, userMealId, mealFoodList);
        this.validateMealFoodList(mealFoodList);
        return mealFoodList;
    }

    private void validateMealFoodList(List<MealFood> mealFoodList) throws MealFoodCanNotInserted {
        if (mealFoodList.isEmpty()) {
            throw new MealFoodCanNotInserted();
        }
    }

    private void insert(List<Food> foodList, int userMealId, List<MealFood> mealFoodList) throws UnexpectedErrorException {
        try {
            for (Food food : foodList) {
                mealFoodList.add(mealFoodDao.add(userMealId, food.getId()));
            }
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }
}