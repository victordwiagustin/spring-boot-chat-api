package com.ggd.ggdchatapi.util;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtil {
    public final static String TIME_ZONE = "Asia/Jakarta";

    /**
     * Get Time
     * @return Timestamp
     */
    public static Timestamp now() {
        return new Timestamp((new Date()).getTime());
    }
}
