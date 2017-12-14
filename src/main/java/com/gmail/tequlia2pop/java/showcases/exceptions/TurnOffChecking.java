package com.gmail.tequlia2pop.java.showcases.exceptions;

import static com.gmail.tequlia2pop.utils.Print.println;

import java.io.FileNotFoundException;
import java.io.IOException;

//“关闭”被检查的异常。

class WrapCheckedException {
	/**
	 * 生成不同类型的异常。
	 * 这些异常被捕获并包装进了 RuntimeException 对象，所以它们成了这些运行时异常的 "cause" 了。
	 * @param type
	 */
	void throwRuntimeException(int type) {
		try {
			switch (type) {
			case 0:
				throw new FileNotFoundException();
			case 1:
				throw new IOException();
			case 2:
				throw new RuntimeException("Where am I?");
			default:
				return;
			}
		} catch (Exception e) { // 转换为不受检查的异常
			throw new RuntimeException(e);
		}
	}
}

class SomeOtherException extends Exception {
}

public class TurnOffChecking {
	public static void main(String[] args) {
		WrapCheckedException wce = new WrapCheckedException();
		// You can call throwRuntimeException() without a try
		// block, and let RuntimeExceptions leave the method:
		wce.throwRuntimeException(3);
		// 或者你可以选择捕获异常：
		for (int i = 0; i < 4; i++)
			try {
				if (i < 3)
					wce.throwRuntimeException(i);
				else
					throw new SomeOtherException();
			} catch (SomeOtherException e) {
				println("SomeOtherException: " + e);
			} catch (RuntimeException re) {
				try {
					throw re.getCause();
				} catch (FileNotFoundException e) {
					println("FileNotFoundException: " + e);
				} catch (IOException e) {
					println("IOException: " + e);
				} catch (Throwable e) {
					println("Throwable: " + e);
				}
			}
	}
}
