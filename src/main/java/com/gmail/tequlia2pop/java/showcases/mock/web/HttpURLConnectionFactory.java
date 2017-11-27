package com.gmail.tequlia2pop.java.showcases.mock.web;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 基于 HTTP 协议的连接工厂实现。
 */
public class HttpURLConnectionFactory implements ConnectionFactory {

	/**
	 * 连接的 URL。
	 */
	private URL url;

	/**
	 * 构造函数，使用 url 作为参数。
	 * 
	 * @param url
	 */
	public HttpURLConnectionFactory(URL url) {
		this.url = url;
	}

	/*
	 * 从 HTTP 输入流中读取数据。
	 * @see com.gmail.tequlia2pop.java.showcases.mock.ConnectionFactory#getData()
	 */
	@Override
	public InputStream getData() throws Exception {
		HttpURLConnection connection = (HttpURLConnection) this.url.openConnection();
		return connection.getInputStream();
	}
}
