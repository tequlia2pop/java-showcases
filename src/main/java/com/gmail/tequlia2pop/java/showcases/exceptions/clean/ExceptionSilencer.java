package com.gmail.tequlia2pop.java.showcases.exceptions.clean;

public class ExceptionSilencer {
	public static void main(String[] args) {
		try {
			throw new RuntimeException();
		} finally {
			// 在 finally 块里面使用 return 会丢失抛出的异常。
			return;
		}
	}
}
