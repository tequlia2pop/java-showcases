package com.gmail.tequlia2pop.utils.container;

import java.util.LinkedHashMap;

import com.gmail.tequlia2pop.utils.generator.Generator;

/**
 * 一个 Map，它使用生成器对象来填充数据。
 * 
 * @author tequlia2pop
 *
 * @param <K>
 * @param <V>
 */
public class MapData<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = 8506288926367019934L;

	// 泛型便利方法：
	
	public static <K, V> MapData<K, V> map(Generator<Pair<K, V>> gen, int quantity) {
		return new MapData<>(gen, quantity);
	}

	public static <K, V> MapData<K, V> map(Generator<K> genK, Generator<V> genV, int quantity) {
		return new MapData<>(genK, genV, quantity);
	}

	public static <K, V> MapData<K, V> map(Generator<K> genK, V value, int quantity) {
		return new MapData<>(genK, value, quantity);
	}

	public static <K, V> MapData<K, V> map(Iterable<K> genK, Generator<V> genV) {
		return new MapData<>(genK, genV);
	}

	public static <K, V> MapData<K, V> map(Iterable<K> genK, V value) {
		return new MapData<>(genK, value);
	}

	/**
	 * 接收 Pair 生成器的构造器。
	 * 
	 * @param gen Pair 生成器
	 * @param quantity 要生成的键值对的数量
	 */
	public MapData(Generator<Pair<K, V>> gen, int quantity) {
		for (int i = 0; i < quantity; i++) {
			Pair<K, V> p = gen.next();
			put(p.key, p.value);
		}
	}

	/**
	 * 接收键生成器和值生成器的构造器。
	 * 
	 * @param genK 键生成器
	 * @param genV 值生成器
	 * @param quantity 要生成的键值对的数量
	 */
	public MapData(Generator<K> genK, Generator<V> genV, int quantity) {
		for (int i = 0; i < quantity; i++) {
			put(genK.next(), genV.next());
		}
	}

	/**
	 * 接收键生成器和单独的值的构造器。
	 * 
	 * @param genK 键生成器
	 * @param value 单独的值
	 * @param quantity 要生成的键值对的数量
	 */
	public MapData(Generator<K> genK, V value, int quantity) {
		for (int i = 0; i < quantity; i++) {
			put(genK.next(), value);
		}
	}

	/**
	 * 接收键的 Iterable 和值生成器的构造器。
	 * 
	 * @param iterK 键的 Iterable（包括任何 Collection）
	 * @param genV 值生成器
	 */
	public MapData(Iterable<K> iterK, Generator<V> genV) {
		for (K key : iterK) {
			put(key, genV.next());
		}
	}

	/**
	 * 接收键的 Iterable 和单独的值的构造器。
	 * 
	 * @param iterK 键的 Iterable（包括任何 Collection）
	 * @param value 单独的值
	 */
	public MapData(Iterable<K> iterK, V value) {
		for (K key : iterK) {
			put(key, value);
		}
	}
}
