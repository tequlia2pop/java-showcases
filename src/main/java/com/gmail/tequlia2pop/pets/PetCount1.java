package com.gmail.tequlia2pop.pets;

import static com.gmail.tequlia2pop.utils.Print.print;
import static com.gmail.tequlia2pop.utils.Print.println;

import java.util.LinkedHashMap;
import java.util.Map;

import com.gmail.tequlia2pop.pets.creator.LiteralPetCreator;
import com.gmail.tequlia2pop.pets.creator.Pets;
import com.gmail.tequlia2pop.pets.model.Pet;
import com.gmail.tequlia2pop.utils.container.MapData;

// 使用 isInstance()。
public class PetCount1 {
	static class PetCounter extends LinkedHashMap<Class<? extends Pet>, Integer> {
		private static final long serialVersionUID = -7551211926609980717L;

		public PetCounter() {
			super(MapData.map(LiteralPetCreator.allTypes, 0));
		}

		public void count(Pet pet) {
			// Class.isInstance（）消除了 instanceofs：
			for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet())
				if (pair.getKey().isInstance(pet))
					put(pair.getKey(), pair.getValue() + 1);
		}

		@Override
		public String toString() {
			StringBuilder result = new StringBuilder("{");
			for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
				result.append(pair.getKey().getSimpleName());
				result.append("=");
				result.append(pair.getValue());
				result.append(", ");
			}
			result.delete(result.length() - 2, result.length());
			result.append("}");
			return result.toString();
		}
	}
	
	public static void main(String[] args) {
		PetCounter petCounter = new PetCounter();
		for (Pet pet : Pets.createArray(20)) {
			print(pet.getClass().getSimpleName() + " ");
			petCounter.count(pet);
		}
		println();
		println(petCounter);
	}
}
