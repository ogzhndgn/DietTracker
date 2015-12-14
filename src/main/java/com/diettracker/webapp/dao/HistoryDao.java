package com.diettracker.webapp.dao;

import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.History;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

    public List<History> getByFilter(String mealCode, Date mealTimeBegin, Date mealTimeEnd, String foodSearch, int userId) throws DAOException {
        ResultSetHandler<List<History>> resultSetHandler = new BeanListHandler<>(History.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(userId);
        String sql = "SELECT * FROM diettracker.history dh WHERE dh.userid = ? ";
        if (StringUtils.isNotBlank(mealCode)) {
            sql += " AND dh.code = ? ";
            parameterList.add(mealCode);
        }
        if (mealTimeBegin != null && mealTimeEnd != null) {
            sql += " AND dh.eatingtime BETWEEN ? AND ? ";
            parameterList.add(new java.sql.Date(mealTimeBegin.getTime()));
            parameterList.add(new java.sql.Date(mealTimeEnd.getTime()));
        }
        if (StringUtils.isNotBlank(foodSearch)) {
            sql += " AND dh.foodlist ILIKE ? ";
            parameterList.add("%" + foodSearch + "%");
        }
        sql += " ORDER BY eatingtime DESC";
        try {
            return queryRunner.query(sql, resultSetHandler, parameterList.toArray());
        } catch (SQLException e) {
            logger.fatal(e.getMessage() + " " + e.getCause());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}