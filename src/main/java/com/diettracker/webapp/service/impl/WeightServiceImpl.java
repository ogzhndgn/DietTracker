package com.diettracker.webapp.service.impl;

import com.diettracker.webapp.dao.WeightDao;
import com.diettracker.webapp.exception.impl.InvalidDateException;
import com.diettracker.webapp.exception.impl.InvalidWeightException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.Weight;
import com.diettracker.webapp.model.wrapper.SqlDate;
import com.diettracker.webapp.service.spec.WeightService;
import com.diettracker.webapp.service.util.UtilityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 23.02.2016.
 */
@Service
public class WeightServiceImpl implements WeightService {
    private final Logger logger = LogManager.getLogger(WeightService.class);

    @Autowired
    WeightDao weightDao;
    @Autowired
    UtilityService utilityService;

    @Override
    public Weight addWeight(int userId, String weightStr, String weightDateStr) throws InvalidDateException, UnexpectedErrorException, InvalidWeightException {
        try {
            double weight = Double.parseDouble(weightStr);
            SqlDate weightSqlDate = utilityService.convertToSqlDate(utilityService.convertDate(weightDateStr));
            return weightDao.add(userId, weight, weightSqlDate);
        } catch (NumberFormatException ne) {
            throw new InvalidWeightException();
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    @Override
    public List<Weight> getWeights(int userId) throws UnexpectedErrorException {
        try {
            List<Weight> weightList = weightDao.get(userId);
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
        List<Double> weightValueList = new ArrayList<>();
        for (Weight weight : weightList) {
            weightValueList.add(weight.getWeight());
        }
        return this.convertObjectToJsonString(weightValueList);
    }

    @Override
    public String getWeightDateJSON(List<Weight> weightList) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMM");
        List<String> weightDateList = new ArrayList<>();
        for (Weight weight : weightList) {
            weightDateList.add(dateFormat.format(weight.getWeightDate()));
        }
        return this.convertObjectToJsonString(weightDateList);
    }

    private String convertObjectToJsonString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Error while converting weight to JSON: ", e);
        }
        return jsonString;
    }

    private List<Weight> setStatuses(List<Weight> weightList) {
        if (weightList.isEmpty()) {
            return weightList;
        }
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