package com.springboot.btest.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String CRON_FORMAT = "ss mm HH dd MM ? yyyy";

    public static String date2cron(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_FORMAT);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }
}
