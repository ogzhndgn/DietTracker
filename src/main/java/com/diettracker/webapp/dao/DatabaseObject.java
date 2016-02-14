package com.diettracker.webapp.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 4.7.2015
 */
@Repository
class DatabaseObject {
    @Autowired
    private BasicDataSource dataSource;

    final Logger logger = LogManager.getLogger(DatabaseObject.class);

    BasicDataSource getDataSource() {
        return dataSource;
    }
}
