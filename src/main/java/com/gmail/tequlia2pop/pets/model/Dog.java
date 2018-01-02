package com.gmail.tequlia2pop.pets.model;

// 狗。
public class Dog extends Pet {

	public Dog() {
		super();
	}

	public Dog(String name) {
		super(name);
	}

	public static class Factory implements com.gmail.tequlia2pop.utils.Factory<Dog> {
		@Override
		public Dog create() {
			return new Dog();
		}
	}
}
