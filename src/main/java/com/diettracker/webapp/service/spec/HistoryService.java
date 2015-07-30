package com.diettracker.webapp.service.spec;

import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.model.History;

import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 22.7.2015
 */
public interface HistoryService {
    public List<History> getLasts(int userId) throws UnexpectedErrorException;

    public List<History> getAll(int userId) throws UnexpectedErrorException;
}
