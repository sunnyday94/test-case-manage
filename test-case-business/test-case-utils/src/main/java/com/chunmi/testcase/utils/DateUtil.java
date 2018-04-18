package com.chunmi.testcase.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

public class DateUtil {

	/**
	 * 获取现在时间
	 *
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * 获取现在时间
	 *
	 * @return返回短时间格式 yyyy-MM-dd
	 */
	public static Date getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * 获取现在时间
	 *
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取现在时间
	 *
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取时间 小时:分;秒 HH:mm:ss
	 *
	 * @return
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 *
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 *
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 *
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 *
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 得到现在时间
	 *
	 * @return
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}

	/**
	 * 提取一个月中的最后一天
	 *
	 * @param day
	 * @return
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	 * 得到现在时间
	 *
	 * @return 字符串 yyyyMMdd HHmmss
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 得到现在小时
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 得到传入时间的小时
	 */
	public static String getHour(Long timestamp) {
		Date currentTime = new Date(timestamp);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 得到现在分钟
	 *
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	 *
	 * @param sformat
	 *            yyyyMMddhhmmss
	 * @return
	 */
	public static String getUserDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	 *
	 * @param sformat
	 *            yyyyMMddhhmmss
	 * @return
	 */
	public static String getUserDate(Date date, String sformat) {
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
	 */
	public static String getTwoHour(String st1, String st2) {
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
			return "0";
		else {
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
			if ((y - u) > 0)
				return y - u + "";
			else
				return "0";
		}
	}

	/**
	 * 得到二个日期间的间隔天数
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(sj1);
			java.util.Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 时间前推或后推分钟,其中JJ表示分钟.
	 */
	public static String getPreTime(String sj1, String jj) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 */
	public static String getNextDay(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 判断是否润年
	 *
	 * @param ddate
	 * @return
	 */
	public static boolean isLeapYear(String ddate) {

		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = strToDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 返回美国时间格式 26 Apr 2006
	 *
	 * @param str
	 * @return
	 */
	public static String getEDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(str, pos);
		String j = strtodate.toString();
		String[] k = j.split(" ");
		return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	}

	/**
	 * 获取一个月的最后一天
	 *
	 * @param dat
	 * @return
	 */
	public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}

	/**
	 * 判断二个时间是否在同一个周
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周
	 *
	 * @return
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}

	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
	 *
	 * @param sdate
	 * @param num
	 * @return
	 */
	public static String getWeek(Date sdate, String num, String format) {
		// 再转换为时间
		Calendar c = Calendar.getInstance();
		c.setTime(sdate);
		if (num.equals("1")) // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2")) // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3")) // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4")) // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5")) // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6")) // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0")) // 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat(format).format(c.getTime());
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 *
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = DateUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static String getWeekStr(String sdate) {
		String str = "";
		str = DateUtil.getWeek(sdate);
		if ("1".equals(str)) {
			str = "星期日";
		} else if ("2".equals(str)) {
			str = "星期一";
		} else if ("3".equals(str)) {
			str = "星期二";
		} else if ("4".equals(str)) {
			str = "星期三";
		} else if ("5".equals(str)) {
			str = "星期四";
		} else if ("6".equals(str)) {
			str = "星期五";
		} else if ("7".equals(str)) {
			str = "星期六";
		}
		return str;
	}

	/**
	 * 两个时间之间的天数
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
	 * 此函数返回该日历第一行星期日所在的日期
	 *
	 * @param sdate
	 * @return
	 */
	public static String getNowMonth(String sdate) {
		// 取该时间所在月的一号
		sdate = sdate.substring(0, 8) + "01";

		// 得到这个月的1号是星期几
		Date date = DateUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int u = c.get(Calendar.DAY_OF_WEEK);
		String newday = DateUtil.getNextDay(sdate, (1 - u) + "");
		return newday;
	}

	/**
	 * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
	 *
	 * @param k
	 *            表示是取几位随机数，可以自己定
	 */

	public static String getNo(int k) {

		return getUserDate("yyyyMMddhhmmss") + getRandom(k);
	}

	/**
	 * 返回一个随机数
	 *
	 * @param i
	 * @return
	 */
	public static String getRandom(int i) {
		Random jjj = new Random();
		// int suiJiShu = jjj.nextInt(9);
		if (i == 0)
			return "";
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}

	/**
	 * @param args
	 */
	public static boolean RightDate(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		;
		if (date == null)
			return false;
		if (date.length() > 10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			sdf.parse(date);
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	// 合同续约
	public static String datexuyue(int year) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		String[] nowStr = now.split("-");
		int year1 = Integer.parseInt(nowStr[0]) + year;
		StringBuffer sb = new StringBuffer();
		sb.append(year1 + "-" + nowStr[1] + "-" + nowStr[2]);
		return sb.toString();
	}

	/**
	 * 获取当月的 天数
	 */
	public static int getCurrentMonthDay() {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 根据年 月 获取对应的月份 天数
	 */
	public static int getDaysByYearMonth(int year, int month) {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 获取每一周的开始时间
	 * 
	 * @param times
	 * @return
	 */
	public static Long getBeginTimeWeek(Long times) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTimeInMillis(times);
		currentDate.setFirstDayOfWeek(Calendar.MONDAY);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return currentDate.getTimeInMillis();
	}

	/**
	 * 获取每一周的结束时间
	 * 
	 * @param times
	 * @return
	 */
	public static Long getEndTimeWeek(Long times) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTimeInMillis(times);
		currentDate.setFirstDayOfWeek(Calendar.MONDAY);
		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return currentDate.getTimeInMillis();
	}

	/**
	 * 获取每一天的开始时间
	 * 
	 * @param times
	 * @return
	 */
	public static Long getBeginTimeDay(Long times) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTimeInMillis(times);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		return currentDate.getTimeInMillis();
	}

	/**
	 * 获取每一天的结束时间
	 * 
	 * @param times
	 * @return
	 */
	public static Long getEndTimeDay(Long times) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTimeInMillis(times);
		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		return currentDate.getTimeInMillis();
	}

	/**
	 * 获取当月的开始时间
	 * 
	 * @param times
	 * @return
	 */
	public static Long getBeginTimeMonth(Long times) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTimeInMillis(times);
		currentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONDAY),
				currentDate.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		currentDate.set(Calendar.DAY_OF_MONTH, currentDate.getActualMinimum(Calendar.DAY_OF_MONTH));
		return currentDate.getTimeInMillis();
	}

	/**
	 * 获取当月的结束时间
	 * 
	 * @param times
	 * @return
	 */
	public static Long getEndTimeMonth(Long times) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTimeInMillis(times);
		currentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONDAY),
				currentDate.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		currentDate.set(Calendar.DAY_OF_MONTH, currentDate.getActualMaximum(Calendar.DAY_OF_MONTH));
		currentDate.set(Calendar.HOUR_OF_DAY, 24);
		return currentDate.getTimeInMillis() - 1000;
	}

	/**
	 * 
	 * @TITLE: getDates
	 * @AUTHOR: mqb
	 * @DESCRIPTION: 获取两个日期之间的所有日期
	 * @PARAM @param startday yyyy-MM-dd HH:mm:ss
	 * @PARAM @param endday yyyy-MM-dd HH:mm:ss endday如果为空就用当前时间
	 * @PARAM @return
	 * @RETURN List<Date>
	 * @THROWS
	 */
	public static List<Date> getDates(String startday, String endday) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Date> lDate = new ArrayList<Date>();
		try {
			Date dBegin = sdf.parse(startday);
			lDate = findDates(dBegin, endday == null ? new Date() : sdf.parse(endday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return lDate;
	}

	/**
	 * @TITLE: findDates
	 * @AUTHOR: mqb
	 * @DESCRIPTION: 获取两个日期之间的所有日期
	 * @PARAM @param dBegin
	 * @PARAM @param dEnd
	 * @PARAM @return
	 * @RETURN List<Date>
	 * @THROWS
	 */
	private static List<Date> findDates(Date dBegin, Date dEnd) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		return lDate;
	}

	/**
	 * 
	 * @TITLE: isSameDay
	 * @AUTHOR: mqb
	 * @DESCRIPTION: 判断两个时间是否是同一天
	 * @PARAM @param timeA
	 * @PARAM @param timeB
	 * @PARAM @return
	 * @RETURN boolean
	 * @THROWS
	 */
	public static boolean isSameDay(Long timeA, Long timeB) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String tA = sf.format(new Date(timeA));
		String tB = sf.format(new Date(timeB));
		return tA.equals(tB) ? true : false;
	}

	/**
	 * @Title: getBeginTimeQuarter
	 * @Author: mqb
	 * @Date: 2016年5月17日 下午12:19:07
	 * @Description: TODO 获取某时间所在季度的开始时间戳
	 * @Param: @param times
	 * @Param: @return
	 * @Return: Long
	 * @Throws:
	 */
	public static Long getBeginTimeQuarter(Long times) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTimeInMillis(times);
		int month = currentDate.get(Calendar.MONTH) + 1;
		if (month >= 1 && month <= 3) {
			currentDate.set(Calendar.MONTH, 0);
		} else if (month >= 4 && month <= 6) {
			currentDate.set(Calendar.MONTH, 3);
		} else if (month >= 7 && month <= 9) {
			currentDate.set(Calendar.MONTH, 6);
		} else if (month >= 10 && month <= 12) {
			currentDate.set(Calendar.MONTH, 9);
		}
		currentDate.set(Calendar.DATE, 1);
		// System.out.println(currentDate.getTime());
		// System.out.println(currentDate.getTimeInMillis());
		// System.out.println(new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss").format(currentDate.getTime()));
		return currentDate.getTimeInMillis();
	}

	/**
	 * @Title: getEndTimeQuarter
	 * @Author: mqb
	 * @Date: 2016年5月17日 下午12:19:47
	 * @Description: TODO 获取某时间所在季度的结束时间戳
	 * @Param: @param times
	 * @Param: @return
	 * @Return: Long
	 * @Throws:
	 */
	public static Long getEndTimeQuarter(Long times) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTimeInMillis(times);
		int month = currentDate.get(Calendar.MONTH) + 1;
		// System.out.println(month);
		if (month >= 1 && month <= 3) {
			currentDate.set(Calendar.MONTH, 2);
			currentDate.set(Calendar.DATE, 31);
		} else if (month >= 4 && month <= 6) {
			currentDate.set(Calendar.MONTH, 5);
			currentDate.set(Calendar.DATE, 30);
		} else if (month >= 7 && month <= 9) {
			currentDate.set(Calendar.MONTH, 8);
			currentDate.set(Calendar.DATE, 30);
		} else if (month >= 10 && month <= 12) {
			currentDate.set(Calendar.MONTH, 11);
			currentDate.set(Calendar.DATE, 31);
		}
		// System.out.println(currentDate.getTime());
		// System.out.println(getEndTimeMonth(currentDate.getTimeInMillis()));
		// System.out.println(new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss").format(getEndTimeMonth(currentDate.getTimeInMillis())));
		return getEndTimeMonth(currentDate.getTimeInMillis());
	}

	/**
	 * @Title: getBeginTimeYear
	 * @Author: mqb
	 * @Date: 2016年5月17日 下午12:20:04
	 * @Description: TODO 获取某时间所在年份的开始时间戳
	 * @Param: @param times
	 * @Param: @return
	 * @Return: Long
	 * @Throws:
	 */
	public static Long getBeginTimeYear(Long times) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTimeInMillis(times);
		currentDate.set(Calendar.MONTH, 0);
		currentDate.set(Calendar.DATE, 1);
		// System.out.println(new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss").format(currentDate.getTime()));
		return currentDate.getTimeInMillis();
	}

	/**
	 * @Title: getEndTimeYear
	 * @Author: mqb
	 * @Date: 2016年5月17日 下午12:20:22
	 * @Description: TODO 获取某时间所在年份的结束时间戳
	 * @Param: @param times
	 * @Param: @return
	 * @Return: Long
	 * @Throws:
	 */
	public static Long getEndTimeYear(Long times) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTimeInMillis(times);
		currentDate.set(Calendar.MONTH, 11);
		currentDate.set(Calendar.DATE, 31);
		// System.out.println(new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss").format(getEndTimeMonth(currentDate.getTimeInMillis())));
		return getEndTimeMonth(currentDate.getTimeInMillis());
	}

	/**
	 * @Title: getEndTimeBeforeDay
	 * @Author: mqb
	 * @Date: 2016年5月19日 下午9:03:54
	 * @Description: TODO 获取前一天的结束时间
	 * @Param: @param times
	 * @Param: @return
	 * @Return: Long
	 * @Throws:
	 */
	public static Long getEndTimeBeforeDay(Long times) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTimeInMillis(times);
		currentDate.add(Calendar.DAY_OF_MONTH, -1);
		return getEndTimeDay(currentDate.getTimeInMillis());
	}

	/**
	 * @Title: getBeginTimeBeforeDay
	 * @Author: mqb
	 * @Date: 2016年5月19日 下午9:04:17
	 * @Description: TODO 获取前一天的开始时间
	 * @Param: @param times
	 * @Param: @return
	 * @Return: Long
	 * @Throws:
	 */
	public static Long getBeginTimeBeforeDay(Long times) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTimeInMillis(times);
		currentDate.add(Calendar.DAY_OF_MONTH, -1);
		return getBeginTimeDay(currentDate.getTimeInMillis());
	}

	/**
	 * @Title: getEndTimeAfterDay
	 * @Author: mqb
	 * @Date: 2016年5月19日 下午9:04:55
	 * @Description: TODO 获取后一天的结束时间
	 * @Param: @param times
	 * @Param: @return
	 * @Return: Long
	 * @Throws:
	 */
	public static Long getEndTimeAfterDay(Long times) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTimeInMillis(times);
		currentDate.add(Calendar.DAY_OF_MONTH, 1);
		return getEndTimeDay(currentDate.getTimeInMillis());
	}

	/**
	 * @Title: getBeginTimeAfterDay
	 * @Author: mqb
	 * @Date: 2016年5月19日 下午9:05:21
	 * @Description: TODO 获取后一天的开始时间
	 * @Param: @param times
	 * @Param: @return
	 * @Return: Long
	 * @Throws:
	 */
	public static Long getBeginTimeAfterDay(Long times) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTimeInMillis(times);
		currentDate.add(Calendar.DAY_OF_MONTH, -1);
		return getBeginTimeDay(currentDate.getTimeInMillis());
	}

	public static String getyearmonthday() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		String yearmonthday = c.get(Calendar.YEAR) + "" + (c.get(Calendar.MONTH) + 1) + c.get(Calendar.DAY_OF_MONTH);
		return yearmonthday;
	}

	/**
	 * @Description :<p class="detail">根据传入的时间获取距离现在的时限(分钟|小时|天)</p>
	 * @Author : <a href="tuojin@chunmi.com">tuojin</a>
	 * @Date : 2017/3/30- 13:49
	 * @Params :
	 * @Returns :
	 */
	public static String getPastedTime(Date date) {
		if (date == null) {
			return "";
		} else {
			Long timeNums = date.getTime();
			Long newTimes = System.currentTimeMillis();
			Long millisecond = newTimes - timeNums;
			Long minute = 60 * 1000l;
			Long hour = minute * 60;
			Long day = hour * 24;

			if (millisecond <= minute) {
				return "刚刚";
			} else if (millisecond > minute && millisecond <= hour) {
				Long minuteNum = millisecond / minute;
				return Math.round(minuteNum) + "分钟前";
			} else if (millisecond > hour && millisecond <= day) {
				Long hourNum = millisecond / hour;
				return Math.round(hourNum) + "小时前";
			} else {
				// Long dayNum = millisecond/day;
				// return Math.round(dayNum)+"天前";
				return dateToStr(date);
			}
		}
	}

}