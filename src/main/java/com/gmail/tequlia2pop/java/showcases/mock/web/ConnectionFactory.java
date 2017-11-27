package com.gmail.tequlia2pop.java.showcases.mock.web;

import java.io.InputStream;

/**
 * 连接工厂接口。
 * 我们有不同的连接工厂，它们都必须实现这个接口以便从一个连接返回一个 InputStream，
 * 无论连接的类型是什么（Http、TCP/IP 等）。这意味着数据内容的提取独立于获取连接的方式。
 * 这种重构技术有时候被成为类工厂重构。
 */
public interface ConnectionFactory {

	/**
	 * 从连接中读取数据。
	 * 
	 * @return
	 * @throws Exception
	 */
	InputStream getData() throws Exception;
}