package com.gmail.tequlia2pop.pets.model;

// 啮齿类动物。
public class Rodent extends Pet {

	public Rodent() {
		super();
	}

	public Rodent(String name) {
		super(name);
	}

	public static class Factory implements com.gmail.tequlia2pop.utils.Factory<Rodent> {
		@Override
		public Rodent create() {
			return new Rodent();
		}
	}
}
