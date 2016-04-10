package com.diettracker.webapp.dao;

import com.diettracker.webapp.enums.PasswordRecoveryStatus;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.model.PasswordRecovery;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;

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
}