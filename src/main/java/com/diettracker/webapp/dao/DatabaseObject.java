package com.diettracker.webapp.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 4.7.2015
 */
@Repository
public class DatabaseObject {
    @Autowired
    private BasicDataSource dataSource;

    final Logger logger = Logger.getLogger(UserDao.class);

    BasicDataSource getDataSource() {
        return dataSource;
    }
}
