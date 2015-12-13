package com.diettracker.webapp.service.impl;

import com.diettracker.webapp.dao.FoodDao;
import com.diettracker.webapp.exception.impl.FoodNotFoundException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.Food;
import com.diettracker.webapp.service.spec.FoodService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 14.7.2015
 */
@Service
public class FoodServiceImpl implements FoodService {

    private static final int FOOD_PREFIX_LIMIT = 10;
    @Autowired
    FoodDao foodDao;

    @Override
    public Food insertNewFood(String name, int userId) throws UnexpectedErrorException {
        try {
            return this.getByName(name, userId);
        } catch (FoodNotFoundException e) {
            return this.addFood(name, userId);
        }
    }

    private Food addFood(String name, int userId) throws UnexpectedErrorException {
        try {
            return foodDao.add(name, userId);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    public Food getByName(String name, int userId) throws UnexpectedErrorException, FoodNotFoundException {
        Food food;
        try {
            food = foodDao.get(StringUtils.trim(name), userId);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
        if (food == null) {
            throw new FoodNotFoundException();
        }
        return food;
    }

    @Override
    public List<Food> getByPrefix(String prefix, int userId) throws FoodNotFoundException, UnexpectedErrorException {
        List<Food> foodList;
        try {
            foodList = foodDao.getByPrefix(prefix, userId, FOOD_PREFIX_LIMIT);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
        if (foodList == null || foodList.isEmpty()) {
            throw new FoodNotFoundException();
        }
        return foodList;
    }

    @Override
    public List<Food> getAllFoodByUser(int userId) throws FoodNotFoundException, UnexpectedErrorException {
        List<Food> foodList;
        try {
            foodList = foodDao.getByUserId(userId);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
        if (foodList == null || foodList.isEmpty()) {
            throw new FoodNotFoundException();
        }
        return foodList;
    }
}