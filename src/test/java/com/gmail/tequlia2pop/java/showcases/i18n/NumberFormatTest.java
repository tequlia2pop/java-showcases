package com.gmail.tequlia2pop.java.showcases.i18n;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.junit.Test;

public class NumberFormatTest {

	@Test
	public void testDecimalFormat() throws ParseException {
		// 打印一个数字；针对给定的语言环境，使用本地化的数字、整数、货币和百分比格式
		double myNumber = -1234.56;

		Locale locale = Locale.CHINA;
		// Locale locale = new Locale("zh", "CN");
		assertEquals(locale.getDisplayName(), "中文 (中国)");

		NumberFormat form = NumberFormat.getInstance(locale);
		assertEquals(((DecimalFormat) form).toPattern(), "#,##0.###");
		assertEquals(form.format(myNumber), "-1,234.56");
		assertEquals(form.parse(form.format(myNumber)), -1234.56);

		form = NumberFormat.getIntegerInstance(locale);
		assertEquals(((DecimalFormat) form).toPattern(), "#,##0");
		assertEquals(form.format(myNumber), "-1,235");
		assertEquals(form.parse(form.format(myNumber)), -1235L);

		form = NumberFormat.getCurrencyInstance(locale);
		assertEquals(((DecimalFormat) form).toPattern(), "¤#,##0.00");
		assertEquals(form.format(myNumber), "-￥1,234.56");
		assertEquals(form.parse(form.format(myNumber)), -1234.56);

		form = NumberFormat.getPercentInstance(locale);
		assertEquals(((DecimalFormat) form).toPattern(), "#,##0%");
		assertEquals(form.format(myNumber), "-123,456%");
		assertEquals(form.parse(form.format(myNumber)), -1234.56);
	}

}
