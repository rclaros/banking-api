/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.utils;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

/**
 *
 * @author RCLAROS
 */
public class DateUtils {

    public static Date getCurrentDate() {
        LocalDateTime nowLocal = new LocalDateTime();
        DateTime nowUTC = nowLocal.toDateTime(DateTimeZone.UTC);
        return nowUTC.toDate();
    }

    public static Date getToDate(Date date) {
        if (date != null) {
            DateTime nowLocal = new DateTime(date);
            DateTime nowUTC = nowLocal.toDateTime(DateTimeZone.UTC);
            return nowUTC.toDate();
        }
        return date;
    }
     public static Date getToDate(Long date) {
        if (date != null) {
            DateTime nowLocal = new DateTime(date);
            DateTime nowUTC = nowLocal.toDateTime(DateTimeZone.UTC);
            return nowUTC.toDate();
        }
        return null;
    }
}
