package com.diettracker.webapp.dao;

import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.UserMeal;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 15.7.2015
 */
@Repository
public class UserMealDao extends DatabaseObject {
    public UserMeal add(int mealId, int userId, Timestamp eatingTime) throws DAOException {
        String sql = "INSERT INTO diettracker.usermeal (mealid, userid, eatingtime) VALUES (?,?,?)";
        ResultSetHandler<UserMeal> resultSetHandler = new BeanHandler<>(UserMeal.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {mealId, userId, eatingTime};
        try {
            return queryRunner.insert(sql, resultSetHandler, params);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}