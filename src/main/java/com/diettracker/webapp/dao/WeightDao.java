package com.diettracker.webapp.dao;

import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.Weight;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 23.02.2016.
 */
@Repository
public class WeightDao extends DatabaseObject {

    public Weight add(int userId, double weight, Date weightDate) throws DAOException {
        String sql = "INSERT INTO diettracker.weight (userid, weight, weightdate, createddate) VALUES (?, ?, ?, now())";
        ResultSetHandler<Weight> resultSetHandler = new BeanHandler<>(Weight.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {userId, weight, weightDate};
        try {
            return queryRunner.insert(sql, resultSetHandler, params);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.fatal(e.getMessage() + " " + e.getCause());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public List<Weight> get(int userId) throws DAOException {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM diettracker.weight WHERE userid = ? ORDER BY weightdate DESC, createddate DESC");
        ResultSetHandler<List<Weight>> resultSetHandler = new BeanListHandler<>(Weight.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {userId};
        try {
            return queryRunner.query(stringBuilder.toString(), resultSetHandler, params);
        } catch (SQLException e) {
            logger.fatal(e.getMessage() + " " + e.getCause());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public void delete(int id, int userId) throws DAOException {
        String sql = "DELETE FROM diettracker.weight dw WHERE dw.id = ? AND dw.userid = ?";
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {id, userId};
        try {
            queryRunner.update(sql, params);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public Weight getLastWeight(int userId) throws DAOException {
        String sql = "SELECT * FROM diettracker.weight dw WHERE dw.userid = ? ORDER BY dw.weightdate DESC, dw.createddate DESC LIMIT 1";
        ResultSetHandler<Weight> resultSetHandler = new BeanHandler<>(Weight.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {userId};
        try {
            return queryRunner.query(sql, resultSetHandler, params);
        } catch (SQLException e) {
            logger.fatal(e.getMessage() + " " + e.getCause());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}