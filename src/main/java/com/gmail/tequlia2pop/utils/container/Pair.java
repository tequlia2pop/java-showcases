package com.gmail.tequlia2pop.utils.container;

/**
 * 表示 Map 的一个键值对。
 * 
 * 该类实际上是一个二维元祖。
 * 
 * @author tequlia2pop
 *
 * @param <K>
 * @param <V>
 */
public class Pair<K, V> {
	
	public final K key;
	
	public final V value;

	public Pair(K k, V v) {
		key = k;
		value = v;
	}
}
