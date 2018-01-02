package com.gmail.tequlia2pop.pets.model;

// 猫。
public class Cat extends Pet {
	
	public Cat() {
		super();
	}

	public Cat(String name) {
		super(name);
	}

	public static class Factory implements com.gmail.tequlia2pop.utils.Factory<Cat> {
		@Override
		public Cat create() {
			return new Cat();
		}
	}
}
