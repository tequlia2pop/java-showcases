package com.gmail.tequlia2pop.utils;

import java.io.PrintStream;

/**
 * 打印便捷工具类。
 * 
 * 可以不使用限定符，而使用静态导入来应用这些打印方法。
 * 
 * @author tequlia2pop
 */
public class Print {

	/**
	 * 打印一个对象，然后换行。
	 * 
	 * @param obj 要打印的 对象
	 */
	public static void println(Object obj) {
		System.out.println(obj);
	}

	/**
	 * 写入一个行分隔符字符串。
	 */
	public static void println() {
		System.out.println();
	}

	/**
	 * 打印一个对象。
	 * 
	 * @param obj 要打印的 对象
	 */
	public static void print(Object obj) {
		System.out.print(obj);
	}

	/**
     * 使用指定的格式字符串和参数执行格式化，并将格式化后的字符串写入一个输出流。
     *
     * @param format 格式字符串
	 * @param args 参数
     *
     * @return 输出流
     */
	public static PrintStream printf(String format, Object... args) {
		return System.out.printf(format, args);
	}
	
}