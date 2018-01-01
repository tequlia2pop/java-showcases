package com.gmail.tequlia2pop.utils.generator;

/**
 * 生成器接口。
 * 
 * 该接口作为工厂方法模式中的 Creator 接口。
 * 
 * @author tequlia2pop
 *
 * @param <T> 要生成的对象的类型
 */
public interface Generator<T> {
	
	/**
	 * 创建并返回一个 T 类型的对象。
	 * 
	 * @return
	 */
	public T next();
}
