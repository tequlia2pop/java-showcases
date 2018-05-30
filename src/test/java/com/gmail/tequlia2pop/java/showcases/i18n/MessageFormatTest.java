package com.gmail.tequlia2pop.java.showcases.i18n;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * 下面给出一些 MessageFormat 的用法例子。
 * 当然，在实际的国际化程序中，消息格式模式和其他静态字符串将从资源包中获取。其他参数在运行时动态确定。
 * 
 * @author tequlia2pop
 */
public class MessageFormatTest {

	// 在模式字符串中，一对单引号可用于引用除单引号之外的任何任意字符；围绕的单引号将被移除。
	// 要表示单引号本身，必须在模式字符串中使用用两个单引号 `''`。
	@Test
	public void testQuotes() {
		assertEquals(MessageFormat.format("'{0}'", new Object()), "{0}");
		assertEquals(MessageFormat.format("'''{'0}''", new Object()), "'{0}'");
		assertEquals(MessageFormat.format("'''{0}'''", new Object()), "'{0}'");
		assertEquals(MessageFormat.format("'{''}'", new Object()), "{'}");

		// 任何不匹配的引用被视为在给定模式结束时关闭。例如，模式字符串 `"'{0}"` 被视为模式 `"'{0}'"`。
		assertEquals(MessageFormat.format("'{0}", new Object()), "{0}");
	}

	@Test
	public void testSubformatPattern() {
		assertEquals("$#31,45", MessageFormat.format("{1,number,$'#',##}", 108, 3145));
	}

	// 使用静态的方法 MessageFormat.format，它在内部创建一个只使用一次的 MessageFormat
	@Test
	public void test1() {
		Calendar cal = Calendar.getInstance();
		cal.set(2014, Calendar.MARCH, 18, 12, 0, 0);// 2014年3月18日
		Date date = cal.getTime();

		int planet = 7;
		String event = "a disturbance in the Force";

		String pattern = "At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.";
		String result = MessageFormat.format(pattern, planet, date, event);
		System.out.println(result);
		assertEquals(result, "At 12:00:00 on 2014-3-18, there was a disturbance in the Force on planet 7.");
	}

	// 创建了一个可以重复使用的 MessageFormat 实例
	@Test
	public void test2() {
		int fileCount = 1273;
		String diskName = "MyDisk";
		Object[] testArgs = { new Long(fileCount), diskName };

		MessageFormat form = new MessageFormat("The disk \"{1}\" contains {0} file(s).");
		String result = form.format(testArgs);
		System.out.println(result);
		assertEquals(result, "The disk \"MyDisk\" contains 1,273 file(s).");
	}

	// 对于更复杂的模式，可以使用 ChoiceFormat 来生成正确的单数和复数形式
	// 使用编程方式创建 ChoiceFormat
	@Test
	public void test3() {
		MessageFormat form = new MessageFormat("The disk \"{1}\" contains {0}.");

		double[] filelimits = { 0, 1, 2 };
		String[] filepart = { "no files", "one file", "{0,number} files" };
		ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
		// 为第一个参数应用新的格式
		form.setFormatByArgumentIndex(0, fileform);

		String diskName = "MyDisk";

		assertEquals(form.format(new Object[] { 0L, diskName }), "The disk \"MyDisk\" contains no files.");
		assertEquals(form.format(new Object[] { 1L, diskName }), "The disk \"MyDisk\" contains one file.");
		assertEquals(form.format(new Object[] { 1273L, diskName }), "The disk \"MyDisk\" contains 1,273 files.");
	}

	// 使用模式创建 ChoiceFormat
	@Test
	public void test4() {
		MessageFormat form = new MessageFormat("The disk \"{1}\" contains {0}.");
		form.applyPattern("There {0,choice,0#are no files|1#is one file|1<are {0,number,integer} files}.");
		
		String diskName = "MyDisk";
		
		assertEquals(form.format(new Object[] { 0L, diskName }), "There are no files.");
		assertEquals(form.format(new Object[] { 1L, diskName }), "There is one file.");
		assertEquals(form.format(new Object[] { 1273L, diskName }), "There are 1,273 files.");
	}

	// 当一个参数在字符串中被多次解析时，最后的匹配将是解析的最终结果
	@Test
	public void test5() {
		MessageFormat mf = new MessageFormat("{0,number,#.##}, {0,number,#.#}");
		Object[] objs = { new Double(3.1415) };
		String result = mf.format(objs);
		assertEquals(result, "3.14, 3.1");
		objs = null;
		objs = mf.parse(result, new ParsePosition(0));
		assertArrayEquals(objs, new Object[] { new Double(3.1) });
	}

	// 使用包含同一参数多个匹配项的模式对 MessageFormat 对象进行解析时将返回最后的匹配
	@Test
	public void test6() {
		MessageFormat mf = new MessageFormat("{0}, {0}, {0}");
		String forParsing = "x, y, z";
		Object[] objs = mf.parse(forParsing, new ParsePosition(0));
		assertArrayEquals(objs, new Object[] { new String("z") });
	}

}
