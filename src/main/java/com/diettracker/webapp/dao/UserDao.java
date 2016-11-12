package com.diettracker.webapp.dao;

import com.diettracker.webapp.enums.Role;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 25.6.2015
 */
@Repository
public class UserDao extends DatabaseObject {

    public List<User> getUsers() {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM diettracker.user");
        ResultSetHandler<List<User>> resultSetHandler = new BeanListHandler<>(User.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        try {
            return queryRunner.query(stringBuilder.toString(), resultSetHandler);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

    public User get(String email) throws DAOException {
        String sql = "SELECT * FROM diettracker.user du WHERE du.email = ?";
        ResultSetHandler<User> resultSetHandler = new BeanHandler<>(User.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        try {
            return queryRunner.query(sql, resultSetHandler, email);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public User get(String email, String passwordHash) throws DAOException {
        String sql = "SELECT * FROM diettracker.user du WHERE du.email = ? AND du.passwordhash = ?";
        ResultSetHandler<User> resultSetHandler = new BeanHandler<>(User.class);
        QueryRunner queryRunner = new QueryRunner(super.getDataSource());
        Object[] params = {email, passwordHash};
        try {
            return queryRunner.query(sql, resultSetHandler, params);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public User get(int id) throws DAOException {
        String sql = "SELECT * FROM diettracker.user du WHERE du.id = ?";
        ResultSetHandler<User> resultSetHandler = new BeanHandler<>(User.class);
        QueryRunner queryRunner = new QueryRunner(super.getDataSource());
        Object[] params = {id};
        try {
            return queryRunner.query(sql, resultSetHandler, params);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public User add(String email, String passwordHash, String passwordSalt) throws DAOException {
        String sql = "INSERT INTO diettracker.user (email, passwordhash, passwordsalt, active, role) VALUES (?,?,?,?,?)";
        ResultSetHandler<User> resultSetHandler = new BeanHandler<>(User.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {email, passwordHash, passwordSalt, true, Role.CLIENT.toString()};
        try {
            return queryRunner.insert(sql, resultSetHandler, params);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public int update(int id, String name) throws DAOException {
        String sql = "UPDATE diettracker.user SET name = ? WHERE id = ?";
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {name, id};
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public int updatePassword(int id, String passwordHash, String passwordSalt) throws DAOException {
        String sql = "UPDATE diettracker.user SET passwordhash = ?, passwordsalt = ? WHERE id = ?";
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {passwordHash, passwordSalt, id};
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}