package com.gmail.tequlia2pop.java.showcases.enumerated;

import static com.gmail.tequlia2pop.utils.Print.print;
import static com.gmail.tequlia2pop.utils.Print.println;

/**
 * 展示枚举类的能力。
 * 
 * @author tequlia2pop
 */
public class EnumClass {

	public static void main(String[] args) {
		for (Shrubbery s : Shrubbery.values()) {
			println(s + " ordinal: " + s.ordinal());
			print(s.compareTo(Shrubbery.CRAWLING) + " ");
			print(s.equals(Shrubbery.CRAWLING) + " ");
			println(s == Shrubbery.CRAWLING);
			println(s.getDeclaringClass());
			println(s.name());
			println("----------------------");
		}
		// 从字符串名称中产生一个枚举值：
		for (String s : "HANGING CRAWLING GROUND".split(" ")) {
			Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
			println(shrub);
		}
	}

}

enum Shrubbery {
	GROUND, CRAWLING, HANGING
}
