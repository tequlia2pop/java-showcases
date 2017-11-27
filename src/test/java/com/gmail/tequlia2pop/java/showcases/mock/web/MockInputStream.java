package com.gmail.tequlia2pop.java.showcases.mock.web;

import java.io.IOException;
import java.io.InputStream;

/**
 * 在我们的测试中所使用的一个自定义的 mock 输入流。
 */
public class MockInputStream extends InputStream {

	/**
	 * 要从中读取的缓冲。
	 */
	private String buffer;

	/**
	 * 流中的当前位置。
	 */
	private int position = 0;

	/**
	 * close() 被调用的次数。
	 */
	private int closeCount = 0;

	public void setBuffer(String buffer) {
		this.buffer = buffer;
	}

	@Override
	public int read() throws IOException {
		if (position == this.buffer.length()) {
			return -1;
		}
		return this.buffer.charAt(this.position++);
	}

	@Override
	public void close() throws IOException {
		closeCount++;
		super.close();
	}

	/**
	 * 检验 close() 是否被调用一次。
	 * 
	 * @throws java.lang.AssertionError
	 */
	public void verify() throws java.lang.AssertionError {
		if (closeCount != 1) {
			throw new AssertionError("close() should " + "have been called once and once only");
		}
	}
}
