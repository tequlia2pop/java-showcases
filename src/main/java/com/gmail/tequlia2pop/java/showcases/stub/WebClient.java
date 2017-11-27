package com.gmail.tequlia2pop.java.showcases.stub;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.IOException;

/**
 * Web 客户端类的示例，它打开一个 HTTP 连接并从 Web 服务器上读取响应。
 */
public class WebClient {
	public String getContent(URL url) {
		StringBuffer content = new StringBuffer();
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			InputStream is = connection.getInputStream();
			byte[] buffer = new byte[2048];
			int count;
			while (-1 != (count = is.read(buffer))) {
				content.append(new String(buffer, 0, count));
			}
		} catch (IOException e) {
			return null;
		}
		return content.toString();
	}
}
