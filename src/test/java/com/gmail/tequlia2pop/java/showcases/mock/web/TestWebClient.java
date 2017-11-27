package com.gmail.tequlia2pop.java.showcases.mock.web;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import com.gmail.tequlia2pop.java.showcases.mock.web.WebClient;

/**
 * WebClient 的测试用例。
 */
public class TestWebClient {
	@Test
	public void testGetContentOk() throws Exception {
		MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();
		mockConnectionFactory.setData(new ByteArrayInputStream("It works".getBytes()));

		WebClient client = new WebClient();
		String result = client.getContent(mockConnectionFactory);
		assertEquals("It works", result);
	}
}
