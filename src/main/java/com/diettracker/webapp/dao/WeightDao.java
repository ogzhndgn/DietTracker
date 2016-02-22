package com.diettracker.webapp.dao;

import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.UserMeal;
import com.diettracker.webapp.model.Weight;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 23.02.2016.
 */
@Repository
public class WeightDao extends DatabaseObject {

    public Weight add(int userId, double weight, Date weightDate) throws DAOException {
        String sql = "INSERT INTO diettracker.weight (userid, weight, weightdate, createddate) VALUES (?, ?, ?, now())";
        ResultSetHandler<Weight> resultSetHandler = new BeanHandler<>(Weight.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {userId, weight, new java.sql.Date(weightDate.getTime())};
        try {
            return queryRunner.insert(sql, resultSetHandler, params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.fatal(e.getMessage() + " " + e.getCause());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}
