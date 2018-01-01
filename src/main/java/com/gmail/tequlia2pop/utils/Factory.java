package com.gmail.tequlia2pop.utils;

/**
 * 工厂接口，实际上是工厂方法模式的 Creator 接口。
 * 
 * 该接口会将对象的创建工作交给类自己去完成。
 * 工厂方法可以被多态地调用，从而为你创建恰当类型的对象。
 * 
 * @author tequlia2pop
 *
 * @param <T> 要返回的对象的类型，在每种 Factory 实现中会返回不同的类型。
 * 			这里利用了协变返回类型。
 */
public interface Factory<T> {
	T create();
}
