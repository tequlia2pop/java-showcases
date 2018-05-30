package com.gmail.tequlia2pop.java.showcases.i18n;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

public class DateFormatTest {

	@Test
	public void testDateFormat() throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.set(2014, Calendar.MARCH, 18, 0, 0, 0);// 2014年3月18日
		Date date = cal.getTime();
		// System.out.println("date: " + date);
		assertEquals(date.toString(), "Tue Mar 18 00:00:00 CST 2014");
		
		DateFormat df = DateFormat.getInstance();
		// equals:
		// df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		assertEquals(df.format(date), "14-3-18 上午12:00");
		
		df = DateFormat.getDateTimeInstance();
		// equals:
		// df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		assertEquals(df.format(date), "2014-3-18 0:00:00");
		
		df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CHINA);
		String dateStr = df.format(date);
		assertEquals(dateStr, "2014年3月18日 上午12时00分00秒");

		// 解析
		Date newDate = df.parse(dateStr);
		assertEquals(newDate.toString(), "Tue Mar 18 00:00:00 CST 2014");
	}
	
	@Test
	public void testSimpleDateFormat(){
		Calendar cal = Calendar.getInstance();
		cal.set(2001, Calendar.JULY, 4, 12, 8, 56);// 2001-07-04 12:08:56
		Date date = cal.getTime();
		// System.out.println("date: " + date);
		assertEquals(date.toString(), "Wed Jul 04 12:08:56 CST 2001");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.US);
		// df.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));// 美国太平洋时区
		assertEquals(df.format(date), "2001.07.04 AD at 12:08:56 CST");
		
		df.applyPattern("EEE, MMM d, ''yy");
		assertEquals(df.format(date), "Wed, Jul 4, '01");
		
		df.applyPattern("h:mm a");
		assertEquals(df.format(date), "12:08 PM");
		
		df.applyPattern("hh 'o''clock' a, zzzz");
		assertEquals(df.format(date), "12 o'clock PM, China Standard Time");
		
		df.applyPattern("K:mm a, z");
		assertEquals(df.format(date), "0:08 PM, CST");
		
		df.applyPattern("yyyyy.MMMMM.dd GGG hh:mm aaa");
		assertEquals(df.format(date), "02001.July.04 AD 12:08 PM");
		
		df.applyPattern("EEE, d MMM yyyy HH:mm:ss Z");
		assertEquals(df.format(date), "Wed, 4 Jul 2001 12:08:56 +0800");
		
		df.applyPattern("yyMMddHHmmssZ");
		assertEquals(df.format(date), "010704120856+0800");
		
		df.applyPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		System.out.println(df.format(date));
	}
}
