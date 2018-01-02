package com.gmail.tequlia2pop.pets.model;

// 宠物。
public class Pet extends Individual {

	public Pet() {
		super();
	}

	public Pet(String name) {
		super(name);
	}

	// 为每种特定类型创建类工厂：
	public static class Factory implements com.gmail.tequlia2pop.utils.Factory<Pet> {
		@Override
		public Pet create() {
			return new Pet();
		}
	}
}