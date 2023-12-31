package com.example.template.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author created by sunjy on 12/21/23
 */
public class DateUtils {

    public static final DateTimeFormatter FORMATTER_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter FORMATTER_YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter FORMATTER_YYYY_MM_DD_SLASH = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static final DateTimeFormatter FORMATTER_MM_DD_YY = DateTimeFormatter.ofPattern("MM/dd/yy");

    public static String currentDate(DateTimeFormatter formatter) {
        LocalDateTime now = LocalDateTime.now();
        return formatLocalDateTime(now, formatter);
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        return formatter.format(localDateTime);
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeString, DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        ZoneId zoneId = ZoneId.systemDefault();
        return date.toInstant().atZone(zoneId).toLocalDateTime();
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

}
