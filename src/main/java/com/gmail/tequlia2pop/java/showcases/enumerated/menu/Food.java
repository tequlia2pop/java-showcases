//  使用接口对枚举分类组织。
package com.gmail.tequlia2pop.java.showcases.enumerated.menu;

public interface Food {
	enum Appetizer implements Food {// 开胃菜
		SALAD, SOUP, SPRING_ROLLS;
	}

	enum MainCourse implements Food {// 主菜
		LASAGNE, BURRITO, PAD_THAI, LENTILS, HUMMOUS, VINDALOO;
	}

	enum Dessert implements Food {// 甜点
		TIRAMISU, GELATO, BLACK_FOREST_CAKE, FRUIT, CREME_CARAMEL;
	}

	enum Coffee implements Food {// 咖啡
		BLACK_COFFEE, DECAF_COFFEE, ESPRESSO, LATTE, CAPPUCCINO, TEA, HERB_TEA;
	}
}
