package com.gmail.tequlia2pop.pets.creator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.gmail.tequlia2pop.pets.model.Pet;

/**
 * 随机选取一个宠物类型，来创建宠物的随机序列。
 * 
 * @author tequlia2pop
 */
public abstract class PetCreator {

	private Random rand = new Random(47);

	/**
	 * 创建要创建的不同的宠物类型的列表。
	 * 注意，其中类的类型被指定为“任何从 Pet 导出的类”。
	 * 
	 * 该方法由子类实现（模板方法的变体）。
	 * 
	 * @return
	 */
	public abstract List<Class<? extends Pet>> types();

	/**
	 * 创建一个随机的宠物。
	 * @return
	 */
	public Pet randomPet() {
		int n = rand.nextInt(types().size());
		try {
			return types().get(n).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
		// 违反了 Java 安全机制，例如默认构造器是 private。
		catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 创建由指定数量的随机宠物组成的数组。
	 * @param size
	 * @return
	 */
	public Pet[] createArray(int size) {
		Pet[] result = new Pet[size];
		for (int i = 0; i < size; i++)
			result[i] = randomPet();
		return result;
	}

	/**
	 * 创建由指定数量的随机宠物组成的列表。
	 * @param size
	 * @return
	 */
	public ArrayList<Pet> arrayList(int size) {
		ArrayList<Pet> result = new ArrayList<>();
		Collections.addAll(result, createArray(size));
		return result;
	}
}
