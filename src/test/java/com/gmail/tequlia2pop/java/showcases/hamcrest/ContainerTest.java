package com.gmail.tequlia2pop.java.showcases.hamcrest;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.hamcrest.collection.IsMapContaining;
import org.hamcrest.number.OrderingComparison;
import org.junit.Test;

// 对容器进行测试。这里在 JUnit 中引入了 Hamcrest 匹配器。
public class ContainerTest {

	@Test
	public void testAssertStringList() {
		List<String> actual = Arrays.asList("a", "b", "c");
		List<String> expected = Arrays.asList("a", "b", "c");

		// All passed / true  

		// 1. Test equal.  
		assertThat(actual, is(expected));

		// 2. If List has this value?  
		assertThat(actual, hasItems("b"));

		// 3. Check List Size  
		assertThat(actual, IsCollectionWithSize.hasSize(3));

		assertThat(actual.size(), is(3));

		// 4. List order  

		// Ensure Correct order  
		assertThat(actual, IsIterableContainingInOrder.contains("a", "b", "c"));

		// Can be any order  
		assertThat(actual, IsIterableContainingInAnyOrder.containsInAnyOrder("c", "b", "a"));

		// 5. check empty list  
		assertThat(actual, not(IsEmptyCollection.empty()));

		assertThat(new ArrayList<>(), IsEmptyCollection.empty());
	}

	@Test
	public void testAssertIntegerList() {
		List<Integer> actual = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

		// All passed / true  

		// 1. Test equal.  
		assertThat(actual, is(expected));

		// 2. Check List has this value  
		assertThat(actual, hasItems(2));

		// 3. Check List Size  
		assertThat(actual, IsCollectionWithSize.hasSize(5));

		assertThat(actual.size(), is(5));

		// 4.  List order  

		// Ensure Correct order  
		assertThat(actual, IsIterableContainingInOrder.contains(1, 2, 3, 4, 5));

		// Can be any order  
		assertThat(actual, IsIterableContainingInAnyOrder.containsInAnyOrder(5, 4, 3, 2, 1));

		// 5. check empty list  
		assertThat(actual, not(IsEmptyCollection.empty()));

		assertThat(new ArrayList<>(), IsEmptyCollection.empty());

		// 6. Test numeric comparisons  
		assertThat(actual, everyItem(OrderingComparison.greaterThanOrEqualTo(1)));

		assertThat(actual, everyItem(OrderingComparison.lessThan(10)));
	}

	@Test
	public void testAssertBeanList() {
		List<Fruit> list = Arrays.asList(new Fruit("Banana", 99), new Fruit("Apple", 20));

		// Test equals  
		assertThat(list, hasItems(new Fruit("Banana", 99), new Fruit("Apple", 20)));

		assertThat(list,
				IsIterableContainingInAnyOrder.containsInAnyOrder(new Fruit("Apple", 20), new Fruit("Banana", 99)));

		// Test class property, and its value
		// java.lang.AssertionError: 
		// Expected: iterable over [hasProperty("name", is "Apple"), hasProperty("name", is "Banana")] in any order
		// assertThat(list, containsInAnyOrder(hasProperty("name", is("Apple")), hasProperty("name", is("Banana"))));
		/*assertThat(list,
				IsIterableContainingInAnyOrder.containsInAnyOrder(HasPropertyWithValue.hasProperty("name", is("Apple")),
						HasPropertyWithValue.hasProperty("name", is("Banana"))));*/
	}

	@Test
	public void testAssertMap() {
		Map<String, String> map = new HashMap<>();
		map.put("j", "java");
		map.put("c", "c++");
		map.put("p", "python");
		map.put("n", "node");

		Map<String, String> expected = new HashMap<>();
		expected.put("n", "node");
		expected.put("c", "c++");
		expected.put("j", "java");
		expected.put("p", "python");

		// All passed / true  

		// 1. Test equal, ignore order  
		assertThat(map, is(expected));

		// 2. Test size  
		assertThat(map.size(), is(4));

		// 3. Test map entry, best!  
		assertThat(map, IsMapContaining.hasEntry("n", "node"));

		assertThat(map, not(IsMapContaining.hasEntry("r", "ruby")));

		//4. Test map key  
		assertThat(map, IsMapContaining.hasKey("j"));

		//5. Test map value  
		assertThat(map, IsMapContaining.hasValue("node"));
	}

	class Fruit {
		private String name;
		private int qty;

		public Fruit(String name, int qty) {
			this.name = name;
			this.qty = qty;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getQty() {
			return qty;
		}

		public void setQty(int qty) {
			this.qty = qty;
		}

		// Test equal, override equals() and hashCode()  
		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Fruit fruit = (Fruit) o;
			return qty == fruit.qty && Objects.equals(name, fruit.name);
		}

		@Override
		public int hashCode() {
			return Objects.hash(name, qty);
		}
	}

}