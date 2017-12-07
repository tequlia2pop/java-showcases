// 创建一个“枚举的枚举”。
package com.gmail.tequlia2pop.java.showcases.enumerated.menu;

import com.gmail.tequlia2pop.utils.Enums;

public enum Meal {
	APPETIZER(Food.Appetizer.class), //
	MAINCOURSE(Food.MainCourse.class), //
	DESSERT(Food.Dessert.class), //
	COFFEE(Food.Coffee.class);

	private Food[] values;

	private Meal(Class<? extends Food> kind) {
		values = kind.getEnumConstants();
	}

	public Food randomSelection() {
		return Enums.random(values);
	}

	public interface Food {
		enum Appetizer implements Food {
			SALAD, SOUP, SPRING_ROLLS;
		}

		enum MainCourse implements Food {
			LASAGNE, BURRITO, PAD_THAI, LENTILS, HUMMOUS, VINDALOO;
		}

		enum Dessert implements Food {
			TIRAMISU, GELATO, BLACK_FOREST_CAKE, FRUIT, CREME_CARAMEL;
		}

		enum Coffee implements Food {
			BLACK_COFFEE, DECAF_COFFEE, ESPRESSO, LATTE, CAPPUCCINO, TEA, HERB_TEA;
		}
	}

	// 随机生成一份菜单。
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			for (Meal meal : Meal.values()) {
				Food food = meal.randomSelection();
				System.out.println(food);
			}
			System.out.println("---");
		}
	}
}
