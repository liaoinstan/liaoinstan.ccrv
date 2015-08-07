package com.example.ccrvlz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {
	
	private static GregorianCalendar gc=new GregorianCalendar(); 

	/**
	 * 获取当前时间
	 * @param format
	 * @return
	 */
	public static String getNowTime(String format) {
		return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
	}
	
	/**
	 * 日期转字符串  格式:yyyy年MM月dd日 E (带星期)
	 * @param date
	 * @return
	 */
	public static String getTime(Date date) {
		if (date == null) {
			return "";
		}else {
			return new SimpleDateFormat("yyyy年MM月dd日 E").format(date);
		}
	}
	
	/**
	 * 日期转字符串  格式:yyyy/MM/dd
	 * @param date
	 * @return
	 */
	public static String getTimeSimple(Date date) {
		if (date == null) {
			return "";
		}else {
			return new SimpleDateFormat("yyyy/MM/dd").format(date);
		}
	}
	
	/**
	 * 根据格式日期转字符串 
	 * @param date
	 * @return
	 */
	public static String getTimeFor(String format,Date date) {
		if (date == null) {
			return "";
		}else {
			return new SimpleDateFormat(format).format(date);
		}
	}
	
	/**
	 * 根据格式转日期
	 * @param format
	 * @param dateStr
	 * @return
	 */
	public static Date getDateByStr(String format,String dateStr) {
		SimpleDateFormat sdf =   new SimpleDateFormat( format );
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}
	
	/**
	 * 去掉日期后面的时分秒
	 * @param date
	 * @return
	 */
	public static Date getDateNoSC(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String temp = sdf.format(date);
		try {
			date = sdf.parse(temp);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}
	
	/**
	 * 日期加减
	 * @return
	 */
	public static Date add(Date date,int field,int value) {
		gc.setTime(date);
		gc.add(field, value);
		return gc.getTime();
	}
	   
	public static String cutDateStr(String dateStr) {
		return dateStr.substring(0, 7);
	}

	public static void main(String[] args) {
//		System.out.println(TimeUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
//		System.out.println(TimeUtil.getNowTime("yyyy年MM月dd日 HH:mm"));
		System.out.println(TimeUtil.getTime(new Date()));
	}
}
