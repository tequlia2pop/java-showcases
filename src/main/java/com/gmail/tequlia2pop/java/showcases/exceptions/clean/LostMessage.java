package com.gmail.tequlia2pop.java.showcases.exceptions.clean;

// 如何丢失一个异常。

class VeryImportantException extends Exception {
	@Override
	public String toString() {
		return "A very important exception!";
	}
}

class HoHumException extends Exception {
	@Override
	public String toString() {
		return "A trivial exception";
	}
}

public class LostMessage {
	void f() throws VeryImportantException {
		throw new VeryImportantException();
	}

	void dispose() throws HoHumException {
		throw new HoHumException();
	}

	public static void main(String[] args) {
		try {
			LostMessage lm = new LostMessage();
			try {
				lm.f();
			} finally {
				// 应该把所有抛出异常的方法（如 dispose()），全部打包放入 try-catch 子句中。
				lm.dispose();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
