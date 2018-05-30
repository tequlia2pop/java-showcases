package com.gmail.tequlia2pop.java.showcases.i18n;

import static org.junit.Assert.*;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;

import org.junit.Test;

public class ChoiceFormatTest {

	// 以下是一个显示格式化和解析的简单例子：
	@Test
	public void test1() {
		double[] limits = { 1, 2, 3, 4, 5, 6, 7 };
		String[] dayOfWeekNames = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
		ChoiceFormat form = new ChoiceFormat(limits, dayOfWeekNames);
		ParsePosition status = new ParsePosition(0);
		for (double i = 0.0; i <= 8.0; ++i) {
			status.setIndex(0);
			System.out.println(i + " -> " + form.format(i) + " -> " + form.parse(form.format(i), status));
		}
	}

	// 以下是一个较复杂的带模式格式的例子： 
	@Test
	public void test2() {
		double[] filelimits = { 0, 1, 2 };
		String[] filepart = { "are no files", "is one file", "are {0} files" };
		ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);

		Format[] testFormats = { fileform, null, NumberFormat.getInstance() };
		MessageFormat pattform = new MessageFormat("There {0} on {1}");
		pattform.setFormats(testFormats);

		Object[] testArgs = { new Integer(0), "ADisk" };
		assertEquals(pattform.format(testArgs), "There are no files on ADisk");

		testArgs = new Object[] { new Integer(1), "ADisk" };
		assertEquals(pattform.format(testArgs), "There is one file on ADisk");

		testArgs = new Object[] { new Integer(2), "ADisk" };
		assertEquals(pattform.format(testArgs), "There are 2 files on ADisk");

		testArgs = new Object[] { new Integer(3), "ADisk" };
		assertEquals(pattform.format(testArgs), "There are 3 files on ADisk");
	}

	// 为 ChoiceFormat 对象指定一个模式是相当直接的。
	@Test
	public void test3() {
		ChoiceFormat fmt = new ChoiceFormat(
				"-1#is negative| 0#is zero or fraction | 1#is one |1.0<is 1+ |2#is two |2<is more than 2.");
		System.out.println("Formatter Pattern : " + fmt.toPattern());

		System.out.println("Format with -INF : " + fmt.format(Double.NEGATIVE_INFINITY));
		System.out.println("Format with -1.0 : " + fmt.format(-1.0));
		System.out.println("Format with 0 : " + fmt.format(0));
		System.out.println("Format with 0.9 : " + fmt.format(0.9));
		System.out.println("Format with 1.0 : " + fmt.format(1));
		System.out.println("Format with 1.5 : " + fmt.format(1.5));
		System.out.println("Format with 2 : " + fmt.format(2));
		System.out.println("Format with 2.1 : " + fmt.format(2.1));
		System.out.println("Format with NaN : " + fmt.format(Double.NaN));
		System.out.println("Format with +INF : " + fmt.format(Double.POSITIVE_INFINITY));
	}

}
