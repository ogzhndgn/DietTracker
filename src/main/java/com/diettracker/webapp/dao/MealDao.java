package com.diettracker.webapp.dao;

import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.Meal;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 6.7.2015
 */
@Repository
public class MealDao extends DatabaseObject {

    public List<Meal> getMealList() throws DAOException {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM diettracker.meal");
        ResultSetHandler<List<Meal>> resultSetHandler = new BeanListHandler<>(Meal.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        try {
            return queryRunner.query(stringBuilder.toString(), resultSetHandler);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public Meal get(int id) throws DAOException {
        String sql = "SELECT * FROM diettracker.meal du WHERE du.id = ?";
        ResultSetHandler<Meal> resultSetHandler = new BeanHandler<>(Meal.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        try {
            return queryRunner.query(sql, resultSetHandler, id);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

}
