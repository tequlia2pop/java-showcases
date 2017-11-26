package com.gmail.tequlia2pop.java.showcases.containers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 展示了 Collection 的拷贝构造函数可用于浅拷贝，
 * 并提供了通过迭代来对集合进行深拷贝的技术。
 * 
 * @author tequlia2pop
 */
public class CollectionCloningTest {
	public static void main(String args[]) {
		// deep cloning Collection in Java 
		Collection<Employee> org = new HashSet<>();
		org.add(new Employee("Joe", "Manager"));
		org.add(new Employee("Tim", "Developer"));
		org.add(new Employee("Frank", "Developer"));
		System.out.println("Original Collection: " + org);

		// creating copy of Collection using copy constructor 
		Collection<Employee> shallow = new HashSet<>(org);
		System.out.println("Shallow Copy of Collection: " + shallow);

		// deep Cloning List in Java 
		Collection<Employee> deep = new HashSet<>(org.size());
		Iterator<Employee> iterator = org.iterator();
		while (iterator.hasNext()) {
			deep.add(iterator.next().clone());
		}
		System.out.println("Deep Copy of Collection: " + deep);

		// modify original Collection
		Iterator<Employee> itr = org.iterator();
		while (itr.hasNext()) {
			itr.next().setDesignation("staff");
		}
		System.out.println("Original Collection after modification: " + org);
		System.out.println("Shallow Copy of Collection with modification: " + shallow);
		System.out.println("Deep Copy of Collection without modification: " + deep);
	}
}

class Employee implements Cloneable {
	private String name;
	private String designation;

	public Employee(String name, String designation) {
		this.name = name;
		this.designation = designation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public Employee clone() {
		try {
			return (Employee) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e); // won't happen 
		}
	}

	@Override
	public String toString() {
		return String.format("%s: %s", name, designation);
	}
}
