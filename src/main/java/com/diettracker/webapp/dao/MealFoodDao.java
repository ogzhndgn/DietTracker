package com.diettracker.webapp.dao;

import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.MealFood;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 20.7.2015
 */
@Repository
public class MealFoodDao extends DatabaseObject {

    public MealFood add(int mealId, int foodId) throws DAOException {
        String sql = "INSERT INTO diettracker.mealfood (usermealid, foodid) VALUES (?, ?)";
        ResultSetHandler<MealFood> resultSetHandler = new BeanHandler<>(MealFood.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {mealId, foodId};
        try {
            return queryRunner.insert(sql, resultSetHandler, params);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public void delete(int mealFoodId) throws DAOException {
        String sql = "DELETE FROM diettracker.mealfood mf WHERE mf.usermealid = ?";
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        try {
            queryRunner.update(sql, mealFoodId);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}
