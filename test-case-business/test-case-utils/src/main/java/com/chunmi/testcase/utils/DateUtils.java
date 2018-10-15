package com.chunmi.testcase.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;



/**
 * @author sunny
 *
 */
public class DateUtils {

	//private static SimpleDateFormat df = null;

	public static String dateToYMDHMS(Date date){
		if(date==null)return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	/**返回：yyyyMMddHHmmss格式
	 * @param date
	 * @return
	 */
	public static String toYMDHMS(Date date){
		if(date==null)return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return df.format(date);
	}

	/**返回：yyyyMM格式
	 * @param date
	 * @return
	 */
	public static String dateToYM(Date date){
		if(date==null)return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		return df.format(date);
	}

	public static String dateToYMD(Date date){
		if(date==null)return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	public static Date StrToDate(String str) {

	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = null;
	    try {
	     date = format.parse(str);
	    } catch (ParseException e) {
	     e.printStackTrace();
	    }
	    return date;
	 }

	public static  Date jsonTodate(String json){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		sdf.setLenient(false);
		Date d=null;
		try {
			d= sdf.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String toString(Date date,String format){
		if(date==null)return null;
		if(StringUtils.isBlank(format))
			format="yyyy-MM-dd";
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static Date getNow(){
		return new Date();
	}

	public static boolean isDateString(String str, String formatString) {
		SimpleDateFormat format = new SimpleDateFormat(formatString);
	    try {
	    	format.parse(str);
	    	return true;
	    } catch (ParseException e) {
	     e.printStackTrace();
	    }
		return false;
	}

	public static boolean isDateString(String str) {
		// TODO Auto-generated method stub
		return isDateString(str, "yyyy-MM-dd");
	}
}
