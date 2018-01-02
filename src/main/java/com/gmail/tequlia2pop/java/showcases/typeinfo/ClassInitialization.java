package com.gmail.tequlia2pop.java.showcases.typeinfo;

import java.util.Random;

/**
 * 类的初始化有效地实现了尽可能的“惰性”，
 * 它被延迟到了对静态方法或者非常数静态域的首次引用时才执行。
 * 
 * 如果一个 `static` 域不是 `final` 的，那么在对它访问时，要先进行链接和初始化。
 * 如果一个 `static final` 值是“编译期常量”，那么不需要对类进行初始化就可以读取这个值。
 * 但是，如果一个 `static final` 域不是一个编译期常量，
 * 那么对它的访问也会强制进行类的初始化；
 * 例如 `static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);`。
 * 
 * @author tequlia2pop
 */
public class ClassInitialization {
	public static Random rand = new Random(47);

	public static void main(String[] args) throws Exception {
		Class<?> initable = Initable.class;
		System.out.println("After creating Initable ref");
		// 不会触发初始化：
		System.out.println(Initable.staticFinal);
		// 会触发初始化：
		System.out.println(Initable.staticFinal2);
		System.out.println();

		// 会触发初始化：
		System.out.println(Initable2.staticNonFinal);
		System.out.println();

		// 会触发初始化：
		Class<?> initable3 = Class.forName("com.gmail.tequlia2pop.java.showcases.typeinfo.Initable3");
		System.out.println("After creating Initable3 ref");
		System.out.println(Initable3.staticNonFinal);
	}
}

class Initable {
	static final int staticFinal = 47;
	static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);
	static {
		System.out.println("Initializing Initable");
	}
}

class Initable2 {
	static int staticNonFinal = 147;
	static {
		System.out.println("Initializing Initable2");
	}
}

class Initable3 {
	static int staticNonFinal = 74;
	static {
		System.out.println("Initializing Initable3");
	}
}
