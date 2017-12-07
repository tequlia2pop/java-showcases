package com.gmail.tequlia2pop.java.showcases.enumerated;

import static com.gmail.tequlia2pop.utils.Print.print;
import static com.gmail.tequlia2pop.utils.Print.println;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * 使用反射来分析 enum。
 */

enum Explore {
	HERE, THERE
}

public class Reflection {
	public static Set<String> analyze(Class<?> enumClass) {
		println("----- Analyzing " + enumClass + " -----");
		println("Interfaces:");
		for (Type t : enumClass.getGenericInterfaces())
			println("  " + t);
		println("Base: " + enumClass.getSuperclass());
		println("Methods: ");
		Set<String> methods = new TreeSet<String>();
		for (Method m : enumClass.getMethods())
			methods.add(m.getName());
		println(methods);
		return methods;
	}

	public static void main(String[] args) {
		Set<String> exploreMethods = analyze(Explore.class);
		Set<String> enumMethods = analyze(Enum.class);
		println();
		println("Explore.containsAll(Enum)? " + exploreMethods.containsAll(enumMethods));
		print("Explore.removeAll(Enum): ");
		exploreMethods.removeAll(enumMethods);
		print(exploreMethods);
		// 反编译 enum 的代码：
		// OSExecute.command("javap Explore");
	}
}
