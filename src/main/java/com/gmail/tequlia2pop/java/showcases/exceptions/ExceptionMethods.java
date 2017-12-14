package com.gmail.tequlia2pop.java.showcases.exceptions;

import static com.gmail.tequlia2pop.utils.Print.*;

/**
 * 演示 Exception 的方法。
 * 
 * @author tequlia2pop
 */
public class ExceptionMethods {
	public static void main(String[] args) {
		try {
			throw new Exception("My Exception");
		} catch (Exception e) {
			// 可以发现每个方法都比前一个提供了更多的信息——实际上它们每一个都是前一个的超集。
			println("Caught Exception");
			println("getMessage(): " + e.getMessage());
			println("getLocalizedMessage(): " + e.getLocalizedMessage());
			println("toString(): " + e);
			println("printStackTrace(): ");
			e.printStackTrace(System.out);
		}
	}
}
