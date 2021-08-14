package com.mingchang.BlogService.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import java.util.Locale;
import java.util.TimeZone;

public class TimerUtility {

//    private static final Locale LOCALE = Locale.TAIWAN;

    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("Asia/Taipei");

    public static SimpleDateFormat getSimpleDateFormat(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        return sdf;
    }

    public static  Date getNowDate() {
        Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cSchedStartCal.setTimeZone(TIME_ZONE);
        return cSchedStartCal.getTime();
    }

    public static  String getNowDateString() {
        Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cSchedStartCal.setTimeZone(TIME_ZONE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(cSchedStartCal.getTime());
    }

    public static String getNowTimestamp() {
        Date date = new Date();
        long currentTimestamp = date.getTime();
        return String.valueOf(currentTimestamp);
    }

    public static  String getNowTime() {
        Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cSchedStartCal.setTimeZone(TIME_ZONE);
        cSchedStartCal.add(Calendar.SECOND, 219);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(cSchedStartCal.getTime());
    }

    public static Date addMin(Date nowDate, int min) {
        Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cSchedStartCal.setTime(nowDate);
        cSchedStartCal.add(Calendar.MINUTE, min);
        return cSchedStartCal.getTime();
    }

    public static Date addDate(Date nowDate, int date) {
        Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cSchedStartCal.setTime(nowDate);
        cSchedStartCal.add(Calendar.DATE, date);
        return cSchedStartCal.getTime();
    }

    public static Date getDate(int year, int month, int day, int hourOfDay, int minute, int second) {
        Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cSchedStartCal.setTimeZone(TIME_ZONE);
        cSchedStartCal.set(year, month, day, hourOfDay, minute, second);
        return cSchedStartCal.getTime();
    }

    public static Date getYesterdayStart() {
        // Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        // cSchedStartCal.setTimeZone(TIME_ZONE);
        Calendar cSchedStartCal = Calendar.getInstance();
        cSchedStartCal.add(Calendar.DATE, -1);
        cSchedStartCal.set(Calendar.HOUR_OF_DAY, 0);
        cSchedStartCal.set(Calendar.MINUTE, 0);
        cSchedStartCal.set(Calendar.SECOND, 0);
        return cSchedStartCal.getTime();
    }

    public static Date getYesterdayEnd() {
        // Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        // cSchedStartCal.setTimeZone(TIME_ZONE);
        Calendar cSchedStartCal = Calendar.getInstance();
        cSchedStartCal.set(Calendar.HOUR_OF_DAY, 0);
        cSchedStartCal.set(Calendar.MINUTE, 0);
        cSchedStartCal.set(Calendar.SECOND, 0);
        return cSchedStartCal.getTime();
    }

    public static Date getTodayEnd() {
        // Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        // cSchedStartCal.setTimeZone(TIME_ZONE);
        Calendar cSchedStartCal = Calendar.getInstance();
        cSchedStartCal.add(Calendar.DATE, +1);
        cSchedStartCal.set(Calendar.HOUR_OF_DAY, 0);
        cSchedStartCal.set(Calendar.MINUTE, 0);
        cSchedStartCal.set(Calendar.SECOND, 0);
        return cSchedStartCal.getTime();
    }

    public static String getLastDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 28);
        return String.valueOf(calendar.getActualMaximum(Calendar.DATE));
    }

    public static boolean is24Hour(Date date1) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal2.setTimeZone(TIME_ZONE);

        long Intervals = cal2.getTimeInMillis() - cal1.getTimeInMillis();

        return ((cal2.getTimeInMillis() - cal1.getTimeInMillis()) < 86400000);
    }

    public static boolean is10Minute(Date date1) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal2.setTimeZone(TIME_ZONE);

        long Intervals = cal2.getTimeInMillis() - cal1.getTimeInMillis();

        return ((cal2.getTimeInMillis() - cal1.getTimeInMillis()) < 360000);
    }

    public static String addSec(Date nowDate, int second) {
        Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cSchedStartCal.setTime(nowDate);
        cSchedStartCal.setTimeZone(TIME_ZONE);

        // cSchedStartCal.add(Calendar.SECOND, second );
        // 系統時間慢了219秒, 無法更正
        cSchedStartCal.add(Calendar.SECOND, second + 219);
        String str_time = "";
        int hour = cSchedStartCal.get(Calendar.HOUR_OF_DAY);
        int min = cSchedStartCal.get(Calendar.MINUTE);

        if (hour < 10)
            str_time = "0" + hour;
        else
            str_time = String.valueOf(hour);

        if (min < 10)
            str_time += ":0" + min;
        else
            str_time += ":" + min;
        return str_time;
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static String getNowDateNotTime() {
        Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cSchedStartCal.setTimeZone(TIME_ZONE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(cSchedStartCal.getTime());
    }

    public static String dateNotTimeStirng(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static String getRocNowDateString() {
        Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cSchedStartCal.setTimeZone(TIME_ZONE);
        Date nowDate = cSchedStartCal.getTime();
        int year = getYear(nowDate) - 1911;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
        return String.valueOf(year) + dateFormat.format(cSchedStartCal.getTime());
    }

    public static final String getRocNowDateStringHaveSSSS() {
        Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cSchedStartCal.setTimeZone(TIME_ZONE);
        Date nowDate = cSchedStartCal.getTime();
        int year = getYear(nowDate) - 1911;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmssSSSS");
        return String.valueOf(year) + dateFormat.format(cSchedStartCal.getTime());
    }

    public static String vidsToRoc(Date vids) {
        Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cSchedStartCal.setTimeZone(TIME_ZONE);
        int year = getYear(vids) - 1911;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
        return String.valueOf(year) + dateFormat.format(vids);
    }

    public static String vidsToRocNotTime(Date vids) {
        Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cSchedStartCal.setTimeZone(TIME_ZONE);
        int year = getYear(vids) - 1911;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");
        return String.valueOf(year) + dateFormat.format(vids);
    }

    public static String vidsToRocMonth(Date vids) {
        Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cSchedStartCal.setTimeZone(TIME_ZONE);
        int year = getYear(vids) - 1911;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        return String.valueOf(year) + dateFormat.format(vids);
    }

}
