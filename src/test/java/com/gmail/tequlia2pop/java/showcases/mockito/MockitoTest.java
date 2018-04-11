package com.gmail.tequlia2pop.java.showcases.mockito;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class MockitoTest {

	@Test
	public void beginVerify() {
		// 创建 mock
		List mockedList = mock(List.class);

		// 使用 mock 对象——它不会抛出任意的“未受检查的交互”异常
		mockedList.add("one");
		mockedList.clear();

		// 可选择的、明确的、高度可读的验证
		verify(mockedList).add("one");
		verify(mockedList).clear();
	}

	@Test
	public void someStubbing() {
		// 你可以 mock 具体类，而不仅仅是接口
		LinkedList mockedList = mock(LinkedList.class);

		// stubbing
		when(mockedList.get(0)).thenReturn("first");
		when(mockedList.get(1)).thenThrow(new RuntimeException());

		// 下面会打印 "first"
		System.out.println(mockedList.get(0));

		// 下面会抛出运行时异常
		//System.out.println(mockedList.get(1));

		// 下面会打印 "null"，因为 get(999) 尚未被 stub
		System.out.println(mockedList.get(999));

		// 尽管可以验证 stub 的调用，但这通常是多余的
		// 如果你的代码关心 get(0) 返回的东西，那么其他东西就会中断（通常甚至在执行 verify() 之前）。
		// 如果你的代码不关心 get(0) 返回的内容，那么它不应该被 stub。
		verify(mockedList).get(0);
	}

	@Test
	public void argumentMatchers() {
		LinkedList mockedList = mock(LinkedList.class);

		// 使用内建的 anyInt() 参数匹配器，执行 stubbing
		when(mockedList.get(anyInt())).thenReturn("element");

		// 使用自定义的匹配器（假设 isValid()）返回你自己的匹配器实现，执行 stubbing
		// when(mockedList.contains(argThat(isValid()))).thenReturn("element");

		// 下面会打印 "element"
		System.out.println(mockedList.get(999));

		// 你也可以使用参数匹配器来验证
		verify(mockedList).get(anyInt());

		// 参数匹配器也可以写成 Java 8 Lambdas
		// verify(mockedList).add(argThat(someString -> someString.length() > 5));
	}

	@Test
	public void verifyInvocationNumbers() {
		LinkedList mockedList = mock(LinkedList.class);

		// 使用 mock
		mockedList.add("once");

		mockedList.add("twice");
		mockedList.add("twice");

		mockedList.add("three times");
		mockedList.add("three times");
		mockedList.add("three times");

		// 下面两个验证工作完全相同——verify()默认使用 times(1)
		verify(mockedList).add("once");
		verify(mockedList, times(1)).add("once");

		// 确切的调用次数验证
		verify(mockedList, times(2)).add("twice");
		verify(mockedList, times(3)).add("three times");

		// 使用 never() 进行验证。never() 是 times(0) 的别名
		verify(mockedList, never()).add("never happened");

		// 使用 atLeast()/atMost() 进行验证
		verify(mockedList, atLeastOnce()).add("three times");
		verify(mockedList, atLeast(2)).add("three times");
		verify(mockedList, atMost(5)).add("three times");
	}
}
