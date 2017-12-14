package com.gmail.tequlia2pop.java.showcases.exceptions;

/**
 * 编程式地访问栈轨迹信息。
 * 
 * @author tequlia2pop
 */
public class WhoCalled {
	static void f() {
		// Generate an exception to fill in the stack trace
		try {
			throw new Exception();
		} catch (Exception e) {
			for (StackTraceElement ste : e.getStackTrace())
				System.out.println(ste.getMethodName());
		}
	}

	static void g() {
		f();
	}

	static void h() {
		g();
	}

	public static void main(String[] args) {
		f();
		System.out.println("--------------------------------");
		g();
		System.out.println("--------------------------------");
		h();
	}
} 
