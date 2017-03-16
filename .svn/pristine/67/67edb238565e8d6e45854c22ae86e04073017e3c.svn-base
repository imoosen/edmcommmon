package com.fiveone.edm.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月30日 下午2:16:10
 * @version: 1.0
 * @since: JDK1.7
 */
public class DateUtil {
	
	public final static String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";
	public final static String FORMAT_DATE = "yyyy-MM-dd";
	public final static String FORMAT_TIME_SHORT = "yyyyMMddHHmmss";
	public final static String FORMAT_DATE_SHORT = "yyyyMMdd";
	public final static int UNIT_YEAR = 1;
	public final static int UNIT_MONTH = 2;
	public final static int UNIT_DAY = 3;
	
	/**
	 * 指定日期的加减
	 *
	 * @param strDate
	 * @param timeUnit
	 * @param numVal
	 * @param field
	 * @return
	 * 2016-5-19
	 */
	public static String getAddDate(String strDate, int timeUnit, 
			                        int numVal, String field){
		SimpleDateFormat sdf = new SimpleDateFormat(field);
		Date date = null;
		try {
			//字符串转date
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		//date转Calendar
		c.setTime(date);
		//加减日期
		if(timeUnit == UNIT_YEAR) {
			c.add(Calendar.YEAR, numVal);
		}else if(timeUnit == UNIT_MONTH) {
			c.add(Calendar.MONTH, numVal);
		}else if(timeUnit == UNIT_DAY) {
			c.add(Calendar.DATE, numVal);
		}
		//Calendar转Date
		date = c.getTime();
		return sdf.format(date);
	}
	
	/**
	 * 计算两个日期相差的天数
	 *
	 * @param first
	 * @param second
	 * @return
	 * 2016-5-19
	 */
	public static int dayDiffer(Date first, Date second) {
		long firstTime = first.getTime();
		long secondTime = second.getTime();
		long time = Math.abs(firstTime - secondTime);
		return (int)(time / (24 * 60 * 60 * 1000));
	}
	
	/**
	 * 指定日期获取Date
	 * 
	 * @param date
	 * @param field
	 * @return
	 * 2016-5-19
	 */
	public static Date getAssignDate(String date, String field) {
		SimpleDateFormat sdf = new SimpleDateFormat(field);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 指定时间获取字符串时间
	 *
	 * @param date
	 * @param field
	 * @return
	 * 2016-6-3
	 */
	public static String getAssignDate(Date date, String field) {
		if(date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(field);
		return sdf.format(date);
	}
	
	/**
	 * 转换成短格式
	 *
	 * @param date
	 * @return
	 * 2016-6-3
	 */
	public static String getShortDate(String date) {
		if(date.length() <= 10) {
			return date;
		}
		return date.substring(0, 10);
	}

	/*public static void main(String[] args) {
		System.out.println(getAddDate("2016-1-23", DateUtil.UNIT_DAY, 1500, DateUtil.FORMAT_DATE));
		Date d1 = getAssignDate("2017-1-1", DateUtil.FORMAT_DATE);
		Date d2 = getAssignDate("2018-1-1", DateUtil.FORMAT_DATE);
		System.out.println(dayDiffer(d1, d2));

	}*/
}
