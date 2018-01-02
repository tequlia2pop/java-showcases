package com.gmail.tequlia2pop.pets.model;

// 威尔士人的马恩岛猫。
public class Cymric extends Manx {

	public Cymric() {
		super();
	}

	public Cymric(String name) {
		super(name);
	}

	public static class Factory implements com.gmail.tequlia2pop.utils.Factory<Cymric> {
		@Override
		public Cymric create() {
			return new Cymric();
		}
	}
}
