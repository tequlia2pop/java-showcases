package com.gmail.tequlia2pop.java.showcases.stub;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * HttpURLConnection 的 stub 类。
 * 我们重写 getInputStream() 方法来返回"It works"字符串。
 */
public class StubHttpURLConnection extends HttpURLConnection {

	private boolean isInput = true;

	protected StubHttpURLConnection(URL url) {
		super(url);
	}

	@Override
	public InputStream getInputStream() throws IOException {
		if (!isInput) {
			throw new ProtocolException("Cannot read from URLConnection" + " if doInput=false (call setDoInput(true))");
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(new String("It works").getBytes());
		return bais;
	}

	@Override
	public void disconnect() {
	}

	@Override
	public void connect() throws IOException {
	}

	@Override
	public boolean usingProxy() {
		return false;
	}
}
