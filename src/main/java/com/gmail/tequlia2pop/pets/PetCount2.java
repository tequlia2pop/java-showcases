package com.gmail.tequlia2pop.pets;

import static com.gmail.tequlia2pop.utils.Print.print;
import static com.gmail.tequlia2pop.utils.Print.println;

import com.gmail.tequlia2pop.pets.creator.Pets;
import com.gmail.tequlia2pop.pets.model.Pet;
import com.gmail.tequlia2pop.utils.TypeCounter;

/**
 * 使用 TypeCounter 对 Pet 计数。
 * 
 * @author tequlia2pop
 */
public class PetCount2 {
	public static void main(String[] args) {
		TypeCounter counter = new TypeCounter(Pet.class);
		for (Pet pet : Pets.createArray(20)) {
			print(pet.getClass().getSimpleName() + " ");
			counter.count(pet);
		}
		println();
		println(counter);
	}
}
