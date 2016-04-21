package com.diettracker.webapp.dao;

import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.PasswordRecovery;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 10.04.2016.
 */
@Repository
public class PasswordRecoveryDao extends DatabaseObject {

    public PasswordRecovery add(int userId, String token, String hash, Timestamp createdTime, Timestamp expirationTime, String passwordRecoveryStatus) throws DAOException {
        String sql = "INSERT INTO diettracker.passwordrecovery (userid, token, hash, createdtime, expirationtime, status) VALUES (?, ?, ?, ?, ?, ?)";
        ResultSetHandler<PasswordRecovery> resultSetHandler = new BeanHandler<>(PasswordRecovery.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {userId, token, hash, createdTime, expirationTime, passwordRecoveryStatus};
        try {
            return queryRunner.insert(sql, resultSetHandler, params);
        } catch (SQLException e) {
            logger.fatal(e.getMessage() + " " + e.getCause());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public PasswordRecovery getByHash(String hash) throws DAOException {
        String sql = "SELECT * FROM diettracker.passwordrecovery dp WHERE dp.hash = ?";
        ResultSetHandler<PasswordRecovery> resultSetHandler = new BeanHandler<>(PasswordRecovery.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {hash};
        try {
            return queryRunner.query(sql, resultSetHandler, params);
        } catch (SQLException e) {
            logger.fatal(e.getMessage() + " " + e.getCause());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public List<PasswordRecovery> get(int userId, String passwordRecoveryStatus, Timestamp now) throws DAOException {
        String sql = "SELECT * FROM diettracker.passwordrecovery dpr WHERE dpr.userid = ? AND dpr.status = ? AND dpr.createdtime < ? AND dpr.expirationtime > ?";
        ResultSetHandler<List<PasswordRecovery>> resultSetHandler = new BeanListHandler<>(PasswordRecovery.class);
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {userId, passwordRecoveryStatus, now, now};
        try {
            return queryRunner.query(sql, resultSetHandler, params);
        } catch (SQLException e) {
            logger.fatal(e.getMessage() + " " + e.getCause());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }

    public int update(int id, Timestamp usingTime, String passwordRecoveryStatus) throws DAOException {
        String sql = "UPDATE diettracker.passwordrecovery SET usingtime = ?, status = ? WHERE id = ?";
        QueryRunner queryRunner = new QueryRunner(getDataSource());
        Object[] params = {usingTime, passwordRecoveryStatus, id};
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DAOException(e.getMessage(), e.getCause());
        }
    }
}