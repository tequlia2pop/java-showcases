package com.gmail.tequlia2pop.java.showcases.stub;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * stub 一个 HTTP 连接，以此来测试 WebClient 类。 
 */
public class TestWebClient {

	@BeforeClass
	public static void setUp() {
		TestWebClient t = new TestWebClient();
		// 使用自定义的 URL 协议处理器。
		URL.setURLStreamHandlerFactory(t.new StubStreamHandlerFactory());
	}

	@Test
	public void testGetContentOk() throws Exception {
		WebClient client = new WebClient();
		String result = client.getContent(new URL("http://localhost/"));
		assertEquals("It works", result);
	}

	private class StubStreamHandlerFactory implements URLStreamHandlerFactory {
		@Override
		public URLStreamHandler createURLStreamHandler(String protocol) {
			return new StubHttpURLStreamHandler();
		}
	}

	private class StubHttpURLStreamHandler extends URLStreamHandler {
		@Override
		protected URLConnection openConnection(URL url) throws IOException {
			return new StubHttpURLConnection(url);
		}
	}
}
