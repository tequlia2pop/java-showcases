package com.gmail.tequlia2pop.pets.model;

// 哈巴狗。
public class Pug extends Dog {

	public Pug() {
		super();
	}

	public Pug(String name) {
		super(name);
	}

	public static class Factory implements com.gmail.tequlia2pop.utils.Factory<Pug> {
		@Override
		public Pug create() {
			return new Pug();
		}
	}
}
