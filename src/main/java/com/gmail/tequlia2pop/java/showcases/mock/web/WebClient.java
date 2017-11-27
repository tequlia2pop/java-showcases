package com.gmail.tequlia2pop.java.showcases.mock.web;

import java.io.IOException;
import java.io.InputStream;

/**
 * Web 客户端类的示例，它打开一个 HTTP 连接并从 Web 服务器上读取响应。
 */
public class WebClient {

	/**
	 * 打开给定 URL 的连接并从中读取内容。如果发生异常，我们将返回 null。
	 * 
	 * @param connectionFactory
	 * @return
	 */
	public String getContent(ConnectionFactory connectionFactory) throws IOException {
		String result;

		StringBuffer content = new StringBuffer();
		InputStream is = null;
		try {
			is = connectionFactory.getData();
			int count;
			while (-1 != (count = is.read())) {
				content.append(new String(Character.toChars(count)));
			}
			result = content.toString();
		} catch (Exception e) {
			result = null;
		}

		// 关闭流。
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				result = null;
			}
		}

		return result;
	}
}
