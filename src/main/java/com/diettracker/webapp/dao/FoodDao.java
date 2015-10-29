package com.diettracker.webapp.dao;

import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.Food;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 14.7.2015
 */
@Repository
public class FoodDao extends DatabaseObject {

    public Food get(String name, int userId) throws DAOException {
        String sql = "SELECT * FROM diettracker.food df WHERE df.name ILIKE ? AND df.userid = ?";
        ResultSetHandler<Food> resultSetHandler = new BeanHandler<>(Food.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {name, userId};
        try {
            return queryRunner.query(sql, resultSetHandler, params);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }


    public Food add(String name, int userId) throws DAOException {
        String sql = "INSERT INTO diettracker.food (name, userid) VALUES (?,?)";
        ResultSetHandler<Food> resultSetHandler = new BeanHandler<>(Food.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {name, userId};
        try {
            return queryRunner.insert(sql, resultSetHandler, params);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public List<Food> getByPrefix(String prefix, int userId, int limit) throws DAOException {
        String sql = "SELECT * FROM diettracker.food df WHERE df.name ILIKE ? AND df.userid = ? LIMIT ?";
        ResultSetHandler<List<Food>> resultSetHandler = new BeanListHandler<>(Food.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {"%" + prefix + "%", userId, limit};
        try {
            return queryRunner.query(sql, resultSetHandler, params);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public List<Food> getByUserId(int userId) throws DAOException {
        String sql = "SELECT * FROM diettracker.food df WHERE df.userid = ?";
        ResultSetHandler<List<Food>> resultSetHandler = new BeanListHandler<>(Food.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {userId};
        try {
            return queryRunner.query(sql, resultSetHandler, params);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}