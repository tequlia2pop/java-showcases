package com.gmail.tequlia2pop.pets.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.gmail.tequlia2pop.pets.model.Cat;
import com.gmail.tequlia2pop.pets.model.Cymric;
import com.gmail.tequlia2pop.pets.model.Dog;
import com.gmail.tequlia2pop.pets.model.EgyptianMau;
import com.gmail.tequlia2pop.pets.model.Hamster;
import com.gmail.tequlia2pop.pets.model.Manx;
import com.gmail.tequlia2pop.pets.model.Mouse;
import com.gmail.tequlia2pop.pets.model.Mutt;
import com.gmail.tequlia2pop.pets.model.Pet;
import com.gmail.tequlia2pop.pets.model.Pug;
import com.gmail.tequlia2pop.pets.model.Rat;
import com.gmail.tequlia2pop.pets.model.Rodent;
import com.gmail.tequlia2pop.utils.Factory;

/**
 * 用于宠物的工厂类。
 * 
 * 这里使用显式的工厂 PetFactory，需要重写基类以扩展 PetFactory。
 * 
 * @author tequlia2pop
 */
public class PetFactory {
	static List<Factory<? extends Pet>> petFactories = new ArrayList<>();

	static {
		petFactories.add(new Pet.Factory());
		petFactories.add(new Cat.Factory());
		petFactories.add(new Cymric.Factory());
		petFactories.add(new Dog.Factory());
		petFactories.add(new EgyptianMau.Factory());
		// petFactories.add(new Gerbil.Factory());
		petFactories.add(new Hamster.Factory());
		petFactories.add(new Manx.Factory());
		petFactories.add(new Mouse.Factory());
		petFactories.add(new Mutt.Factory());
		petFactories.add(new Pug.Factory());
		petFactories.add(new Rat.Factory());
		petFactories.add(new Rodent.Factory());
	}

	private static Random rand = new Random();

	/**
	 * 从 petFactories 中随机选取一个工厂对象，然后创建一个新的 Pet。
	 * 
	 * @return
	 */
	public static Pet createRandom() {
		int n = rand.nextInt(petFactories.size());
		return petFactories.get(n).create();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			System.out.println(PetFactory.createRandom());
	}
}
