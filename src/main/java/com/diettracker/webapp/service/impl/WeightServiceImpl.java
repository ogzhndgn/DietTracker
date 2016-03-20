package com.diettracker.webapp.service.impl;

import com.diettracker.webapp.dao.WeightDao;
import com.diettracker.webapp.exception.impl.FoodNotFoundException;
import com.diettracker.webapp.exception.impl.InvalidDateException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.Weight;
import com.diettracker.webapp.service.spec.WeightService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 23.02.2016.
 */
@Service
public class WeightServiceImpl implements WeightService {
    private final Logger logger = LogManager.getLogger(WeightService.class);

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

    @Override
    public List<Weight> getWeights(int userId) throws UnexpectedErrorException {
        try {
            List<Weight> weightList = weightDao.get(userId);
            for (Weight weight : weightList) {
                logger.info(weight.toString());
            }
            return this.setStatuses(weightList);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    @Override
    public void deleteWeight(int id, int userId) throws UnexpectedErrorException {
        try {
            weightDao.delete(id, userId);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    @Override
    public String getWeightValueJSON(List<Weight> weightList) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Double> weightValueList = new ArrayList<>();
        for (Weight weight : weightList) {
            weightValueList.add(weight.getWeight());
        }
        String jsonWeightValue = "";
        try {
            jsonWeightValue = objectMapper.writeValueAsString(weightValueList);
        } catch (JsonProcessingException e) {
            logger.error("Error while converting weight to JSON: ", e);
        }
        return jsonWeightValue;
    }

    @Override
    public String getWeightDateJSON(List<Weight> weightList) {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMM");
        List<String> weightDateList = new ArrayList<>();
        for (Weight weight : weightList) {
            weightDateList.add(dateFormat.format(weight.getWeightDate()));
        }
        String jsonWeightDate = "";
        try {
            jsonWeightDate = objectMapper.writeValueAsString(weightDateList);
        } catch (JsonProcessingException e) {
            logger.error("Error while converting weight to JSON: ", e);
        }
        return jsonWeightDate;
    }

    private List<Weight> setStatuses(List<Weight> weightList) {
        for (int i = 0; i < weightList.size() - 1; i++) {
            int nextIndex = i + 1;
            if (weightList.get(i).getWeight() > weightList.get(nextIndex).getWeight()) {
                weightList.get(i).setStatus("+");
            } else if (weightList.get(i).getWeight() == weightList.get(nextIndex).getWeight()) {
                weightList.get(i).setStatus("0");
            } else {
                weightList.get(i).setStatus("-");
            }
        }
        weightList.get(weightList.size() - 1).setStatus("0");
        return weightList;
    }
}