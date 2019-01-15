/**
 * Copyright (C) 2000-2006 
Reserved.
 */
package com.vk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class Function Description
 *
 * @author SYSTEM
 * @Date 2015年1月21日
 */
public class DateUtil {

	/**
	 * 获取[min,max]之间的随机数
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandom(int min, int max) {
		return (int) (Math.random() * (max + 1 - min) + min);
	}


	/**
	 * 把字符串转为固定时间格式的字符串(xx:xx)
	 * @param s
	 * @return
	 * @author SYSTEM
	 * Date 2015年1月21日
	 * @version
	 */
	public static String fmString(String s){
		Date dDate;
		String reTime = "";
		try {
			dDate = new SimpleDateFormat("HH:mm").parse(s);
			reTime = new SimpleDateFormat("HH:mm").format(dDate);
		} catch (ParseException e) {
			reTime = "00:00";
		}
		return reTime;
	}

	public static String StringFormatString(String s){
		Date dDate;
		String reTime = "";
		try {
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse(s);
			reTime = new SimpleDateFormat("yyyy-MM-dd").format(dDate);
		} catch (ParseException e) {
			reTime = "00:00";
		}
		return reTime;
	}

	public static Date dateFormatDate(Date date){
		Date fDate = new Date();
		String reDate="";
		reDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		try {
			fDate = new SimpleDateFormat("yyyy-MM-dd").parse(reDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fDate;
	}


	/**
	 * 把Date转为固定时间格式的字符串(yyyy-MM-dd hh:mm)
	 * @param
	 * @return
	 * @author SYSTEM
	 * Date 2015年1月21日
	 * @version
	 */
	public static String fmYYS(Date d){
		String reTime = "";
		if(d!=null){
			reTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(d);
		}else{
			reTime="1970-01-01 00:00";
		}
		return reTime;
	}

	public static String fmPattern(Date d,String pattern){
		String reTime = "";
		if(d!=null){
			reTime = new SimpleDateFormat(pattern).format(d);
		}else{
			reTime="1970-01-01 00:00";
		}
		return reTime;
	}

	public static Date StrToDate(String str) {
		if(str==null||str.equals("")){
			return null;
		}
		SimpleDateFormat format;
		if(str.contains(":")){
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else {
			if(str.length()==7){
				format = new SimpleDateFormat("yyyy-MM");
			}else{
				format = new SimpleDateFormat("yyyy-MM-dd");
			}
		}
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date StrToDate(String str,String pattern) {
		SimpleDateFormat format;
		format = new SimpleDateFormat(pattern);

		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static Date StrToDateWithPattern(String str,String pattern) {

		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取上个月的第一天/最后一天
	 * @param date
	 * @return
	 */
	public static Map<String, String> getFirstday_Lastday_Month(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		Date theDate = calendar.getTime();

		//上个月第一天
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
		day_first = str.toString();

		//上个月最后一天
		calendar.add(Calendar.MONTH, 1);    //加一个月
		calendar.set(Calendar.DATE, 1);        //设置为该月第一天
		calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
		String day_last = df.format(calendar.getTime());
		StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
		day_last = endStr.toString();

		Map<String, String> map = new HashMap<String, String>();
		map.put("first", day_first);
		map.put("last", day_last);
		return map;
	}
	/**
	 * 第一天/最后一天
	 * @param date
	 * @return
	 */
	public static Map<String, String> getFirstday_Lastday_Month_The(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		//第一天
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(date);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
		day_first = str.toString();

		//最后一天
		calendar.add(Calendar.MONTH, 1);    //加一个月
		calendar.set(Calendar.DATE, 1);        //设置为该月第一天
		calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
		String day_last = df.format(calendar.getTime());
		StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
		day_last = endStr.toString();

		Map<String, String> map = new HashMap<String, String>();
		map.put("first", day_first);
		map.put("last", day_last);
		return map;
	}
	/**
	 * 当月第一天
	 * @return
	 */
	public static String getFirstDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();

		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
		return str.toString();

	}
	
    /**
     * 当月最后一天
     * @return
     */
	public static String getLastDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
        return str.toString();

    }


}
