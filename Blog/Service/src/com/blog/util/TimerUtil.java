package com.blog.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimerUtil {
	
//	private static final Locale LOCALE = Locale.TAIWAN;
	private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("Asia/Taipei");
	
	public static final SimpleDateFormat getSimpleDateFormat(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		return sdf;
	}
	
	public static final Date getNowDate(){
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cSchedStartCal.setTimeZone(TIME_ZONE);
		return cSchedStartCal.getTime();
	}
	public static final String getNowDateString(){
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cSchedStartCal.setTimeZone(TIME_ZONE);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String strDate = dateFormat.format(cSchedStartCal.getTime());
		return strDate;
	}	
	
	public static final String getNowTimestamp(){
		Date date= new Date();		 
		long currentTimestamp = date.getTime();
		return String.valueOf(currentTimestamp);
	}

	public static final String getNowTime(){
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cSchedStartCal.setTimeZone(TIME_ZONE);
		cSchedStartCal.add(Calendar.SECOND, 219 );
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String strDate = dateFormat.format(cSchedStartCal.getTime());
		return strDate;
	}
	
	public static final Date addMin(Date nowDate,int min){
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cSchedStartCal.setTime(nowDate);
		cSchedStartCal.add(Calendar.MINUTE, min);
		return cSchedStartCal.getTime();
	}

	public static final Date addDate(Date nowDate,int date){
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cSchedStartCal.setTime(nowDate);
		cSchedStartCal.add(Calendar.DATE, date);
		return cSchedStartCal.getTime();
	}
	
	public static final Date getDate(int year , int month , int day , int hourOfDay ,int minute ,int second){
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cSchedStartCal.setTimeZone(TIME_ZONE);
		cSchedStartCal.set(year, month, day, hourOfDay, minute ,second);
		return cSchedStartCal.getTime();
	}
	
	public static final Date getDate(Date nowDate){
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cSchedStartCal.setTime(nowDate);
		cSchedStartCal.setTimeZone(TIME_ZONE);
		return cSchedStartCal.getTime();
	}
	
	public static final Date getYesterdayStart(){
		//Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		//cSchedStartCal.setTimeZone(TIME_ZONE);
		Calendar cSchedStartCal = Calendar.getInstance();
		cSchedStartCal.add(Calendar.DATE, -1);
		cSchedStartCal.set(Calendar.HOUR_OF_DAY, 0);
		cSchedStartCal.set(Calendar.MINUTE, 0);
		cSchedStartCal.set(Calendar.SECOND, 0);
		return cSchedStartCal.getTime();
	}
	
	public static final Date getYesterdayEnd(){
		//Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		//cSchedStartCal.setTimeZone(TIME_ZONE);
		Calendar cSchedStartCal = Calendar.getInstance();
		cSchedStartCal.set(Calendar.HOUR_OF_DAY, 0);	
		cSchedStartCal.set(Calendar.MINUTE, 0);
		cSchedStartCal.set(Calendar.SECOND, 0);
		return cSchedStartCal.getTime();
	}
	
	
	public static final Date getTodayEnd(){
		//Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		//cSchedStartCal.setTimeZone(TIME_ZONE);
		Calendar cSchedStartCal = Calendar.getInstance();
		cSchedStartCal.add(Calendar.DATE, +1);
		cSchedStartCal.set(Calendar.HOUR_OF_DAY, 0);
		cSchedStartCal.set(Calendar.MINUTE, 0);
		cSchedStartCal.set(Calendar.SECOND, 0);
		return cSchedStartCal.getTime();
	}
	
	
	public static final String getLastDayOfMonth(int year , int month){		
		Calendar calendar = Calendar.getInstance();
		calendar.set(year,month,28);
		String strDay = String.valueOf(calendar.getActualMaximum(Calendar.DATE))  ;
		return strDay;
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
	
	public static final String addSec(Date nowDate,int second){
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cSchedStartCal.setTime(nowDate);
		cSchedStartCal.setTimeZone(TIME_ZONE);
		
		//cSchedStartCal.add(Calendar.SECOND, second );
		//系統時間慢了219秒, 無法更正
		cSchedStartCal.add(Calendar.SECOND, second + 219 );
		String str_time ="";
		int hour = cSchedStartCal.get(Calendar.HOUR_OF_DAY);
		int min =  cSchedStartCal.get(Calendar.MINUTE);
		
		if( hour  < 10)
			str_time = "0" + String.valueOf(hour) ; 
		else
			str_time = String.valueOf(hour) ; 
		
		if( min  < 10)
			str_time += ":0" + String.valueOf(min) ; 
		else
			str_time += ":" + String.valueOf(min) ; 
		return str_time;
	}
	public static final int getYear(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return year;
	}
	public static final int getNowYear(){
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cSchedStartCal.setTimeZone(TIME_ZONE);
		int year = cSchedStartCal.get(Calendar.YEAR);
		return year;
	}
	public static final int getNowMonth(){
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cSchedStartCal.setTimeZone(TIME_ZONE);
		int month = cSchedStartCal.get(Calendar.MONTH);
		return month;
	}
	public static final int getNowDay(){
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cSchedStartCal.setTimeZone(TIME_ZONE);
		int day = cSchedStartCal.get(Calendar.DATE);
		return day;
	}

	public static final String getNowDateNotTime(){
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cSchedStartCal.setTimeZone(TIME_ZONE);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(cSchedStartCal.getTime());
		return strDate;
	}
	public static final String getNowDateNotYear(){
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cSchedStartCal.setTimeZone(TIME_ZONE);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");
		String strDate = dateFormat.format(cSchedStartCal.getTime());
		return strDate;
	}
	public static final String getFirstDateOfMonth(){
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cSchedStartCal.setTimeZone(TIME_ZONE);
		cSchedStartCal.add(Calendar.MONTH, 0);
		cSchedStartCal.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(cSchedStartCal.getTime());
		return strDate;
	}
	
	 
}
