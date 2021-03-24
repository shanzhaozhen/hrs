package com.hbjs.hrscommon.utils;

import org.springframework.scheduling.support.CronExpression;

public class CronUtils {

    /**
     * 校验Cron是否正确
     * @param cron
     * @return
     */
    public static boolean isValidExpression(String cron) {
        try {
            CronExpression.parse(cron);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
