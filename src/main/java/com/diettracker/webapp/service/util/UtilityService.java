package com.diettracker.webapp.service.util;

import com.diettracker.webapp.exception.impl.InvalidDateException;
import com.diettracker.webapp.model.wrapper.SqlDate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 1.01.2017.
 */
@Component
public class UtilityService {

    public static final DateFormat DT_STND_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public Date convertDate(String date) throws InvalidDateException {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        try {
            return DT_STND_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new InvalidDateException();
        }
    }

    public SqlDate convertToSqlDate(Date date) {
        SqlDate sqlDate = null;
        if (date != null) {
            sqlDate = new SqlDate(date.getTime());
        }
        return sqlDate;
    }
}