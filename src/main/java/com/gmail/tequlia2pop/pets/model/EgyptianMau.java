package com.gmail.tequlia2pop.pets.model;

// 埃及猫。
public class EgyptianMau extends Cat {

	public EgyptianMau() {
		super();
	}

	public EgyptianMau(String name) {
		super(name);
	}

	public static class Factory implements com.gmail.tequlia2pop.utils.Factory<EgyptianMau> {
		@Override
		public EgyptianMau create() {
			return new EgyptianMau();
		}
	}
}
