package com.diettracker.webapp.service.spec;

import com.diettracker.webapp.exception.impl.FoodNotFoundException;
import com.diettracker.webapp.exception.impl.InvalidDateException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.model.Weight;
import org.springframework.stereotype.Service;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 23.02.2016.
 */
public interface WeightService {
    Weight addWeight(int userId, String weightStr, String weightDateStr) throws InvalidDateException, FoodNotFoundException, UnexpectedErrorException;
}
