package com.cn.safety.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期和时间相关工具
 * @author tech
 *
 */
public class DateTimeUtils {

	private static final String df1 = "yyyy-MM-dd HH:mm:ss"; 
	private static final String df2 = "yyyy-MM-dd HH:mm-ss"; 
	/*
	 * Date转string
	 */
	public static String dateToStr(Date date,String format){
		SimpleDateFormat df = new SimpleDateFormat(format);
		String s = df.format(date);
		return s;
	}
	/*
	 * string转date
	 */
	public static Date strToDate(String dateStr){
		SimpleDateFormat df = new SimpleDateFormat(df1);
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/*
	 * 比较两个日期相差几天
	 */
	public static int daysOfTwo(Date fDate, Date oDate) {
	       Calendar aCalendar = Calendar.getInstance();
	       aCalendar.setTime(fDate);
	       long time1 = aCalendar.getTimeInMillis();  
	       aCalendar.setTime(oDate);
	       long time2 = aCalendar.getTimeInMillis();    
	       long between_days=(time2-time1)/(1000*3600*24); 
	       return Integer.parseInt(String.valueOf(between_days));  
	    }
	public static void main(String[] args) {
		
		Date date = new Date();
		String s = dateToStr(date,df2);
		System.out.println(s);
		
	}
}










