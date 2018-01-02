package com.gmail.tequlia2pop.pets.model;

// 马恩岛猫。
public class Manx extends Cat {

	public Manx() {
		super();
	}

	public Manx(String name) {
		super(name);
	}

	public static class Factory implements com.gmail.tequlia2pop.utils.Factory<Manx> {
		@Override
		public Manx create() {
			return new Manx();
		}
	}
}
