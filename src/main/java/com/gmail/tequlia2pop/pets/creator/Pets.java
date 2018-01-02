package com.gmail.tequlia2pop.pets.creator;

import java.util.ArrayList;

import com.gmail.tequlia2pop.pets.model.Pet;

/**
 * 用于创建默认 PetCreator 的外观类。
 *  
 * @author tequlia2pop
 */
public class Pets {

	public static final PetCreator creator = new LiteralPetCreator();

	public static Pet randomPet() {
		return creator.randomPet();
	}

	public static Pet[] createArray(int size) {
		return creator.createArray(size);
	}

	public static ArrayList<Pet> arrayList(int size) {
		return creator.arrayList(size);
	}
}
