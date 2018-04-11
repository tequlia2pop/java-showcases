package com.gmail.tequlia2pop.java.showcases.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class MockitoReturnValTest {

	// 如何根据参数值来返回值
	@Test
	public void testReturnValDependOnParamVal() {
		Comparable c = mock(Comparable.class);
		when(c.compareTo("Mockito")).thenReturn(1);
		when(c.compareTo("Eclipse")).thenReturn(2);
		assertEquals(1, c.compareTo("Mockito"));
	}

	// 如何根据参数类型来返回值
	@Test
	public void testReturnValInDependOnParamType() {
		Comparable c = mock(Comparable.class);
		when(c.compareTo(isA(Integer.class))).thenReturn(0);
		assertEquals(0, c.compareTo(new Integer(1)));
	}

	// 如何让返回值不依赖于特定参数
	@Test
	public void testReturnValInDependOnParam() {
		Comparable c = mock(Comparable.class);
		when(c.compareTo(anyInt())).thenReturn(-1);
		assertEquals(-1, c.compareTo(9));
	}

}
