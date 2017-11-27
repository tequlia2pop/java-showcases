package com.gmail.tequlia2pop.java.showcases.mock.web;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gmail.tequlia2pop.java.showcases.mock.web.WebClient;

/**
 * WebClient 类的测试用例。 
 */
public class TestWebClient1 {
	@Test
	public void testGetContentOk() throws Exception {
		MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();
		MockInputStream mockStream = new MockInputStream();
		mockStream.setBuffer("It works");
		mockConnectionFactory.setData(mockStream);

		WebClient client = new WebClient();
		String result = client.getContent(mockConnectionFactory);

		assertEquals("It works", result);
		mockStream.verify();
	}
}
