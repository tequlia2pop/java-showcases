package com.gmail.tequlia2pop.java.showcases.typeinfo.reflect;

import static com.gmail.tequlia2pop.utils.Print.println;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 使用反射来显示类的所有方法，包括那些在基类中定义的方法。
 * 
 * 在编程时，特别是如果不记得一个类是否有某个方法，或者不知道一个类究竟能做些什么，
 * 而又不想通过索引或类的层次结构去查找 JDK 文档，这时这个工具确实能节省很多时间。
 * 
 * {Args: com.gmail.tequlia2pop.java.showcases.typeinfo.reflect.ShowMethods}
 * 
 * @author tequlia2pop
 */
public class ShowMethods {

	/**
	 * 表示用法的字符串。
	 */
	private static String usage = "usage:\n"//
			+ "ShowMethods qualified.class.name\n"//
			+ "To show all methods in class or:\n"//
			+ "ShowMethods qualified.class.name word\n"//
			+ "To search for methods involving 'word'";

	/**
	 * 正则模式，用于将完整的方法特征签名转换为不带包名的方法签名。
	 */
	private static Pattern p = Pattern.compile("\\w+\\.");

	public static void main(String[] args) {
		args = new String[] { "com.gmail.tequlia2pop.java.showcases.typeinfo.reflect.ShowMethods" };
		// args = new String[] { "java.lang.String", "index" };
		if (args.length < 1) {
			println(usage);
			System.exit(0);
		}

		int lines = 0;
		try {
			Class<?> c = Class.forName(args[0]);
			Method[] methods = c.getMethods();
			Constructor<?>[] ctors = c.getConstructors();
			if (args.length == 1) {
				for (Method method : methods)
					println(p.matcher(method.toString()).replaceAll(""));

				for (Constructor<?> ctor : ctors)
					println(p.matcher(ctor.toString()).replaceAll(""));

				lines = methods.length + ctors.length;
			} else {
				for (Method method : methods)
					if (method.toString().indexOf(args[1]) != -1) {
						println(p.matcher(method.toString()).replaceAll(""));
						lines++;
					}
				for (Constructor<?> ctor : ctors)
					if (ctor.toString().indexOf(args[1]) != -1) {
						println(p.matcher(ctor.toString()).replaceAll(""));
						lines++;
					}
			}
		} catch (ClassNotFoundException e) {
			println("No such class: " + e);
		}
	}
}
