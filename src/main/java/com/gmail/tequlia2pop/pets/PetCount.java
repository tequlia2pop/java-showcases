package com.gmail.tequlia2pop.pets;

import static com.gmail.tequlia2pop.utils.Print.print;
import static com.gmail.tequlia2pop.utils.Print.println;

import java.util.HashMap;

import com.gmail.tequlia2pop.pets.creator.LiteralPetCreator;
import com.gmail.tequlia2pop.pets.creator.PetCreator;
import com.gmail.tequlia2pop.pets.creator.Pets;
import com.gmail.tequlia2pop.pets.model.Cat;
import com.gmail.tequlia2pop.pets.model.Dog;
import com.gmail.tequlia2pop.pets.model.Hamster;
import com.gmail.tequlia2pop.pets.model.Manx;
import com.gmail.tequlia2pop.pets.model.Mouse;
import com.gmail.tequlia2pop.pets.model.Mutt;
import com.gmail.tequlia2pop.pets.model.Pet;
import com.gmail.tequlia2pop.pets.model.Pug;
import com.gmail.tequlia2pop.pets.model.Rat;
import com.gmail.tequlia2pop.pets.model.Rodent;

// 使用 instanceof。
public class PetCount {
	static class PetCounter extends HashMap<String, Integer> {
		private static final long serialVersionUID = -5136817352576438885L;

		public void count(String type) {
			Integer quantity = get(type);
			if (quantity == null)
				put(type, 1);
			else
				put(type, quantity + 1);
		}
	}

	public static void countPets(PetCreator creator) {
		PetCounter counter = new PetCounter();
		for (Pet pet : creator.createArray(20)) {
			// 列出每个 pet:
			print(pet.getClass().getSimpleName() + " ");
			if (pet instanceof Pet)
				counter.count("Pet");
			if (pet instanceof Dog)
				counter.count("Dog");
			if (pet instanceof Mutt)
				counter.count("Mutt");
			if (pet instanceof Pug)
				counter.count("Pug");
			if (pet instanceof Cat)
				counter.count("Cat");
			if (pet instanceof Manx)
				counter.count("EgyptianMau");
			if (pet instanceof Manx)
				counter.count("Manx");
			if (pet instanceof Manx)
				counter.count("Cymric");
			if (pet instanceof Rodent)
				counter.count("Rodent");
			if (pet instanceof Rat)
				counter.count("Rat");
			if (pet instanceof Mouse)
				counter.count("Mouse");
			if (pet instanceof Hamster)
				counter.count("Hamster");
		}
		// 显示计数：
		println();
		println(counter);
	}

	public static void main(String[] args) {
		countPets(Pets.creator);
	}
}
