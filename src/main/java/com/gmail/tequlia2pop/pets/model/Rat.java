package com.gmail.tequlia2pop.pets.model;

// 褐家鼠。
public class Rat extends Rodent {

	public Rat() {
		super();
	}

	public Rat(String name) {
		super(name);
	}

	public static class Factory implements com.gmail.tequlia2pop.utils.Factory<Rat> {
		@Override
		public Rat create() {
			return new Rat();
		}
	}
}
