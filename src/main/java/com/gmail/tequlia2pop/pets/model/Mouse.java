package com.gmail.tequlia2pop.pets.model;

// 老鼠。
public class Mouse extends Rodent {

	public Mouse() {
		super();
	}

	public Mouse(String name) {
		super(name);
	}

	public static class Factory implements com.gmail.tequlia2pop.utils.Factory<Mouse> {
		@Override
		public Mouse create() {
			return new Mouse();
		}
	}
}
