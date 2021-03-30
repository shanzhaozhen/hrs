package com.hbjs.hrscommon.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateUtils {

    public static String format(Date date, String pattern) {
        if (date == null || !StringUtils.hasText(pattern)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static Date format(String date, String pattern) {
        if (date == null || !StringUtils.hasText(pattern)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("日期格式转换失败（String To Date）");
            return null;
        }
    }

    /**
     * 获取某年某月第一天
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year,int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return format(cal.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获取某年某月最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        return format(cal.getTime(), "yyyy-MM-dd");
    }

    /**
     * 是否同一个月
     * @param date1
     * @param date2
     * @return
     */
    public static boolean sameMonth(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
    }

    /**
     * date1 是否 date2 上一个月的时间段
     * 例如 date1 = 2020-01-01，date2 = 2020-02-01，则返回true
     * 逻辑： date1 > date2 && 取年月（date1 + 1个月） = 取年月（date2）
     * @param date1
     * @param date2
     * @return
     */
    public static boolean lastMonth(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        //设置月份
        calendar1.set(Calendar.MONTH, calendar1.get(Calendar.MONTH) + 1);
        //设置日历中月份的最小天数
        calendar1.set(Calendar.DAY_OF_MONTH, calendar1.getActualMinimum(Calendar.DAY_OF_MONTH));

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
    }

    /**
     * 获取上个月
     * @param date
     * @return
     */
    public static Date getLastMonthDate(String date) {
        return getLastMonthDate(format(date, "yyyy-MM-dd"));
    }

    /**
     * 获取上个月
     * @param date
     * @return
     */
    public static Date getLastMonthDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
        return cal.getTime();
    }

    /**
     * 获取次月N日
     * @param date
     * @return
     */
    public static Date getNextMonthSomeDay(Date date, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return cal.getTime();
    }

    /**
     * 日期加减
     * @param date
     * @param day
     * @param month
     * @param year
     * @return
     */
    public static Date getPlusDate(Date date, Integer day, Integer month, Integer year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (day != null) {
            cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH) + day);
        }
        if (month != null) {
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + month);
        }
        if (month != null) {
            cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + year);
        }
        return cal.getTime();
    }

}
