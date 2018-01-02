package com.gmail.tequlia2pop.pets.factory;

import java.util.ArrayList;
import java.util.Collections;

import com.gmail.tequlia2pop.pets.model.Pet;

/**
 * 随机选取一个 Pet 注册工厂，来创建宠物的随机序列。
 * 
 * @author tequlia2pop
 */
public class PetCreator {

	/**
	 * 创建一个随机的宠物。
	 * @return
	 */
	public Pet randomPet() {
		return Pet.createRandom();
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
