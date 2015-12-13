package com.diettracker.webapp.service.impl;

import com.diettracker.webapp.dao.HistoryDao;
import com.diettracker.webapp.exception.impl.*;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.History;
import com.diettracker.webapp.service.spec.HistoryService;
import com.diettracker.webapp.service.spec.MealService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 22.7.2015
 */
@Service
public class HistoryServiceImpl implements HistoryService {

    private static final int DUMMY_EMPTY_MEAL_ID = 0;
    private final int MAX_MEAL_LIMIT = 10;
    @Autowired
    HistoryDao historyDao;
    @Autowired
    MealService mealService;

    @Override
    public List<History> getLasts(int userId) throws UnexpectedErrorException {
        try {
            return historyDao.get(userId, MAX_MEAL_LIMIT);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    @Override
    public List<History> getAll(int userId) throws UnexpectedErrorException {
        try {
            return historyDao.get(userId);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    @Override
    public List<History> search(String mealIdStr, String mealTimeBeginStr, String mealTimeEndStr, String foodSearch, int userId) throws ServiceException {
        this.checkBeginAndEndDate(mealTimeBeginStr, mealTimeEndStr);
        int mealId = this.getMealId(mealIdStr);
        Date mealTimeBegin = this.convertDate(mealTimeBeginStr);
        Date mealTimeEnd = this.convertDate(mealTimeEndStr);
        try {
            return historyDao.getByFilter(mealId, mealTimeBegin, mealTimeEnd, foodSearch, userId);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    private void checkBeginAndEndDate(String mealTimeBegin, String mealTimeEnd) throws EndDateCanNotBeEmptyException, StartDateCanNotBeEmptyException {
        if (StringUtils.isNotBlank(mealTimeBegin)) {
            if (StringUtils.isNotBlank(mealTimeEnd)) {
                return;
            } else {
                throw new EndDateCanNotBeEmptyException();
            }
        }
        if (StringUtils.isNotBlank(mealTimeEnd)) {
            throw new StartDateCanNotBeEmptyException();
        }
    }

    private int getMealId(String mealIdStr) throws UnexpectedErrorException, InvalidMealException {
        try {
            int mealId = Integer.parseInt(mealIdStr);
            if (mealId > DUMMY_EMPTY_MEAL_ID) {
                mealService.getMealById(mealId);
            }
            return mealId;
        } catch (NumberFormatException nfe) {
            return DUMMY_EMPTY_MEAL_ID;
        }
    }

    private Date convertDate(String date) throws InvalidDateException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if (StringUtils.isBlank(date)) {
            return null;
        }
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new InvalidDateException();
        }
    }
}