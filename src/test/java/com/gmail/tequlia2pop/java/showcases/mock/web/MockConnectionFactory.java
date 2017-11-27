package com.gmail.tequlia2pop.java.showcases.mock.web;

import java.io.InputStream;

import com.gmail.tequlia2pop.java.showcases.mock.web.ConnectionFactory;

/**
 * ConnectionFactory 接口的 mock 实现。
 */
public class MockConnectionFactory implements ConnectionFactory {

	/**
	 * 连接的输入流。
	 */
	private InputStream inputStream;

	/**
	 * 设置输入流。
	 * 
	 * @param stream
	 */
	public void setData(InputStream stream) {
		this.inputStream = stream;
	}

	@Override
	public InputStream getData() throws Exception {
		return this.inputStream;
	}
}
