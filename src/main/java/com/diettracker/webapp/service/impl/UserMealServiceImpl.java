package com.diettracker.webapp.service.impl;

import com.diettracker.webapp.dao.UserMealDao;
import com.diettracker.webapp.exception.impl.InvalidDateException;
import com.diettracker.webapp.exception.impl.InvalidMealException;
import com.diettracker.webapp.exception.impl.NonExistsingUserMealException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.UserMeal;
import com.diettracker.webapp.service.spec.MealService;
import com.diettracker.webapp.service.spec.UserMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 15.7.2015
 */
@Service
public class UserMealServiceImpl implements UserMealService {
    @Autowired
    UserMealDao userMealDao;
    @Autowired
    MealService mealService;

    @Override
    public UserMeal addUserMeal(String meal, int userId, String eatingTime) throws ServiceException {
        try {
            Timestamp eatingTimestamp = this.convertToTimestamp(eatingTime);
            int mealId = Integer.parseInt(meal);
            mealService.getMealById(mealId);
            return userMealDao.add(mealId, userId, eatingTimestamp);
        } catch (NumberFormatException e) {
            throw new InvalidMealException();
        } catch (ParseException e) {
            throw new InvalidDateException();
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    @Override
    public void deleteUserMeal(int id, int userId) throws UnexpectedErrorException {
        try {
            userMealDao.delete(id, userId);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    @Override
    public UserMeal getUserMeal(int id, int userId) throws UnexpectedErrorException, NonExistsingUserMealException {
        try {
            UserMeal userMeal = userMealDao.get(id, userId);
            if (userMeal == null) {
                throw new NonExistsingUserMealException();
            }
            return userMeal;
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    private Timestamp convertToTimestamp(String eatingTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date parsedDate = dateFormat.parse(eatingTime);
        return new Timestamp(parsedDate.getTime());
    }
}