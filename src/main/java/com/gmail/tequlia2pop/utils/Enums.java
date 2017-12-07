package com.gmail.tequlia2pop.utils;

import java.util.Random;

/**
 * 枚举工具类。
 * 
 * @author tequlia2pop
 */
public class Enums {

	private static Random rand = new Random(47);

	/**
	 * 随机选取一个 enum 实例。
	 * 
	 * <T extends Enum<T>> 表示 T 是一个 enum 实例。
	 * 
	 * @param ec enum 的类型
	 * @return
	 */
	public static <T extends Enum<T>> T random(Class<T> ec) {
		return random(ec.getEnumConstants());
	}

	/**
	 * 从指定的对象数组中随机选取一个对象。
	 * 
	 * @param values T 类型的数组
	 * @return
	 */
	public static <T> T random(T[] values) {
		return values[rand.nextInt(values.length)];
	}

}
