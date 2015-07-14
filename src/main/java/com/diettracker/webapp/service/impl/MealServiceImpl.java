package com.diettracker.webapp.service.impl;

import com.diettracker.webapp.dao.MealDao;
import com.diettracker.webapp.exception.impl.EmptyMealListException;
import com.diettracker.webapp.exception.impl.InvalidMealException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.Meal;
import com.diettracker.webapp.service.spec.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 6.7.2015
 */
@Service
public class MealServiceImpl implements MealService {
    @Autowired
    MealDao mealDao;

    @Override
    public List<Meal> getMealList() throws ServiceException {
        try {
            List<Meal> mealList = mealDao.getMealList();
            if (mealList == null || mealList.isEmpty()) {
                throw new EmptyMealListException();
            }
            return mealList;
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    @Override
    public Meal getMealById(int id) throws UnexpectedErrorException, InvalidMealException {
        try {
            Meal meal = mealDao.get(id);
            if (meal == null) {
                throw new InvalidMealException();
            }
            return meal;
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }
}
