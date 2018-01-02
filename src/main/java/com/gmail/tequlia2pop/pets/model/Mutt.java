package com.gmail.tequlia2pop.pets.model;

// 杂种狗。
public class Mutt extends Dog {

	public Mutt() {
		super();
	}

	public Mutt(String name) {
		super(name);
	}

	public static class Factory implements com.gmail.tequlia2pop.utils.Factory<Mutt> {
		@Override
		public Mutt create() {
			return new Mutt();
		}
	}
}
