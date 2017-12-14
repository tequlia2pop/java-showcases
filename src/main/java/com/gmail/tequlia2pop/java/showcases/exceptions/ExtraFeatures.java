package com.gmail.tequlia2pop.java.showcases.exceptions;

import static com.gmail.tequlia2pop.utils.Print.println;

//进一步修饰异常类。

class MyException extends Exception {
	private int x;

	public MyException() {
	}

	public MyException(String msg) {
		super(msg);
	}

	public MyException(String msg, int x) {
		super(msg);
		this.x = x;
	}

	public int val() {
		return x;
	}

	@Override
	public String getMessage() {
		return "Detail Message: " + x + " " + super.getMessage();
	}
}

public class ExtraFeatures {
	public static void f() throws MyException {
		println("Throwing MyException2 from f()");
		throw new MyException();
	}

	public static void g() throws MyException {
		println("Throwing MyException2 from g()");
		throw new MyException("Originated in g()");
	}

	public static void h() throws MyException {
		println("Throwing MyException2 from h()");
		throw new MyException("Originated in h()", 47);
	}

	public static void main(String[] args) {
		try {
			f();
		} catch (MyException e) {
			e.printStackTrace(System.out);
		}
		try {
			g();
		} catch (MyException e) {
			e.printStackTrace(System.out);
		}
		try {
			h();
		} catch (MyException e) {
			e.printStackTrace(System.out);
			System.out.println("e.val() = " + e.val());
		}
	}
}
