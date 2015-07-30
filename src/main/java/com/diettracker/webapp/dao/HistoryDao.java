package com.diettracker.webapp.dao;

import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.History;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 22.7.2015
 */
@Repository
public class HistoryDao extends DatabaseObject {

    public List<History> get(int userId, int limit) throws DAOException {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM diettracker.history WHERE userid = ? ORDER BY eatingtime DESC LIMIT ?");
        ResultSetHandler<List<History>> resultSetHandler = new BeanListHandler<>(History.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {userId, limit};
        try {
            return queryRunner.query(stringBuilder.toString(), resultSetHandler, params);
        } catch (SQLException e) {
            logger.fatal(e.getMessage() + " " + e.getCause());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public List<History> get(int userId) throws DAOException {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM diettracker.history WHERE userid = ? ORDER BY eatingtime DESC");
        ResultSetHandler<List<History>> resultSetHandler = new BeanListHandler<>(History.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {userId};
        try {
            return queryRunner.query(stringBuilder.toString(), resultSetHandler, params);
        } catch (SQLException e) {
            logger.fatal(e.getMessage() + " " + e.getCause());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}