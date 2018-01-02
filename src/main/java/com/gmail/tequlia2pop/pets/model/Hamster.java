package com.gmail.tequlia2pop.pets.model;

// 仓鼠。
public class Hamster extends Rodent {

	public Hamster() {
		super();
	}

	public Hamster(String name) {
		super(name);
	}

	public static class Factory implements com.gmail.tequlia2pop.utils.Factory<Hamster> {
		@Override
		public Hamster create() {
			return new Hamster();
		}
	}
}
