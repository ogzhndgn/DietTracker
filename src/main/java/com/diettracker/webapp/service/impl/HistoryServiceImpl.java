package com.diettracker.webapp.service.impl;

import com.diettracker.webapp.dao.HistoryDao;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.History;
import com.diettracker.webapp.service.spec.HistoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 22.7.2015
 */
@Service
public class HistoryServiceImpl implements HistoryService {

    private static final Logger logger = Logger.getLogger(HistoryService.class);
    private final int MAX_MEAL_LIMIT = 10;
    @Autowired
    HistoryDao historyDao;

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
}