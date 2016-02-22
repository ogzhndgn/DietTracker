package com.diettracker.webapp.service.impl;

import com.diettracker.webapp.dao.WeightDao;
import com.diettracker.webapp.exception.impl.FoodNotFoundException;
import com.diettracker.webapp.exception.impl.InvalidDateException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.Weight;
import com.diettracker.webapp.service.spec.WeightService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 23.02.2016.
 */
@Service
public class WeightServiceImpl implements WeightService {

    @Autowired
    WeightDao weightDao;

    @Override
    public Weight addWeight(int userId, String weightStr, String weightDateStr) throws InvalidDateException, FoodNotFoundException, UnexpectedErrorException {
        try {
            double weight = Double.parseDouble(weightStr);
            Date weightDate = DateUtils.parseDate(weightDateStr, "dd.MM.yyyy");
            return weightDao.add(userId, weight, weightDate);
        } catch (ParseException e) {
            throw new InvalidDateException();
        } catch (NumberFormatException ne) {
            throw new FoodNotFoundException(); //TODO add a new exception here.
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }
}
