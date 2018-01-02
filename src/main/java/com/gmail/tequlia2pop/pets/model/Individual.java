package com.gmail.tequlia2pop.pets.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 表示个体的基类。
 * 
 * 在基类中注册类工厂，有两种解决方案：
 * （1）使用显式的工厂 PetFactory，需要重写基类以扩展 PetFactory。
 * （2）构造器就是一种工厂方法，可以将类对象存储到 List 中，并使用 newInstance() 来创建对象。
 * 
 * 注意，该类具有内存的排序功能，但是与 equals 不一致。
 * 
 * @author tequlia2pop
 */
// public class Individual extends PetFactory implements Comparable<Individual> {
public class Individual implements Comparable<Individual> {
	
	static List<Class<? extends Pet>> petClasses = new ArrayList<>();

	static {
		Collections.addAll(petClasses, Pet.class, Cat.class, Cymric.class, Dog.class, EgyptianMau.class, Hamster.class,
				Manx.class, Mouse.class, Mutt.class, Pug.class, Rat.class, Rodent.class);
	}
	
	private static Random rand = new Random();

	public static Pet createRandom() {
		int n = rand.nextInt(petClasses.size());
		try {
			return petClasses.get(n).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	// --------------------------------------------

	private static long counter = 0;

	/**
	 * 唯一的标识符（通过对每个对象计数而创建的）。
	 */
	private final long id = counter++;

	/**
	 * 名称。可选。
	 */
	private String name;

	public Individual() {
	}

	public Individual(String name) {
		this.name = name;
	}

	public long id() {
		return id;
	}

	/*
	 * 排序的规则首先按照实际类型排序，然后按照 name 排序，最后按照创建的顺序排序。
	 */
	@Override
	public int compareTo(Individual arg) {
		// 首先按类名比较：
		String first = getClass().getSimpleName();
		String argFirst = arg.getClass().getSimpleName();
		int firstCompare = first.compareTo(argFirst);
		if (firstCompare != 0)
			return firstCompare;

		if (name != null && arg.name != null) {
			int secondCompare = name.compareTo(arg.name);
			if (secondCompare != 0)
				return secondCompare;
		}

		return (arg.id < id) ? -1 : (arg.id == id ? 0 : 1);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Individual))
			return false;
		Individual other = (Individual) obj;
		return id == other.id;
	}

	@Override
	public int hashCode() {
		int result = 17;
		/*if (name != null)
			result = 37 * result + name.hashCode();*/
		// result = 37 * result + (int) id;
		result = 37 * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + (name == null ? "" : " " + name);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			System.out.println(createRandom());
	}
}
