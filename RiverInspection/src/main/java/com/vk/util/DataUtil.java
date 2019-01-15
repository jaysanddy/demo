package com.vk.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vk.response.Result;
import org.apache.commons.collections.map.HashedMap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

public class DataUtil {

	/**
	 * 获取[min,max]之间的随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandm(int min, int max) {
		return (int) (Math.random() * (max + 1 - min) + min);
	}
	
	/**
	 * 获取[min,max]两个经纬度之间的经纬度
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static Double getRandmLngLat(int min, int max,int num,double decimal) {
		return ((int) (Math.random() * (max + 1 - min) + min))*decimal+num;
	}
	
	/**
	 * 获取[min,max]之间的随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandm1(int min, int max) {
		return (int) (min + Math.random() * (max - min));
	}

	/**
	 * 获取long的随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static long getLongRandm(long min, long max) {
		return (long) (min + Math.random() * (max - min));
	}

	/**
	 * 日期加天数
	 */
	public static Date addDay(Date date, int day) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}

	/**
	 * 日期加月份
	 */
	public static Date addMonth(Date date, int month) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	/**
	 * 根据日期返回当前月份有多少天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDaysByMonth(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		String month = sdf.format(date);
		int days = 0;
		switch (month) {
		case "01":
			days = 31;
			break;
		case "03":
			days = 31;
			break;
		case "05":
			days = 31;
			break;
		case "07":
			days = 31;
			break;
		case "08":
			days = 31;
			break;
		case "10":
			days = 31;
			break;
		case "12":
			days = 31;
			break;
		case "04":
			days = 30;
			break;
		case "06":
			days = 30;
			break;
		case "09":
			days = 30;
			break;
		case "11":
			days = 30;
			break;
		case "02":
			if (isLeapYear(date)) {
				days = 29;
			} else {
				days = 28;
			}
			break;
		default:
			break;
		}
		return days;
	}



	/**
	 * 判断一个日期是否是润年
	 */
	public static boolean isLeapYear(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(date);
		Integer y = Integer.valueOf(year);
		if (y % 400 == 0) {
			return true;
		} else if (y % 100 != 0 && y % 4 == 0) {
			return true;
		}
		return false;
	}

}
