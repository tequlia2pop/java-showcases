package com.gmail.tequlia2pop.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 对对象类型计数的通用工具，它会对指定的确切类型及其基类型进行计数。
 * 
 * @author tequlia2pop
 */
public class TypeCounter extends HashMap<Class<?>, Integer> {

	private static final long serialVersionUID = 5180414552465689643L;

	/**
	 * 要进行计数的基类型。
	 */
	private Class<?> baseType;

	public TypeCounter(Class<?> baseType) {
		this.baseType = baseType;
	}

	/**
	 * 对指定的对象进行计数。
	 * 
	 * @param obj
	 */
	public void count(Object obj) {
		Class<?> type = obj.getClass();
		// isAssignableFrom() 用于检查参数对象属于我们感兴趣的继承结构。
		if (!baseType.isAssignableFrom(type))
			throw new RuntimeException(obj + " incorrect type: " + type + ", should be type or subtype of " + baseType);
		countClass(type);
	}

	/**
	 * 对指定的 Class 对象及其超类型进行递归计数。
	 * 
	 * @param type
	 */
	private void countClass(Class<?> type) {
		Integer quantity = get(type);
		put(type, (quantity == null) ? 1 : quantity + 1);
		Class<?> superClass = type.getSuperclass();
		if ((superClass != null) && baseType.isAssignableFrom(superClass))
			countClass(superClass);// recursive
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("{");
		for (Map.Entry<Class<?>, Integer> pair : entrySet()) {
			result.append(pair.getKey().getSimpleName());
			result.append("=");
			result.append(pair.getValue());
			result.append(", ");
		}
		result.delete(result.length() - 2, result.length());
		result.append("}");
		return result.toString();
	}
}
