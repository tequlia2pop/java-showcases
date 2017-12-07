package com.gmail.tequlia2pop.java.showcases.enumerated;

import static com.gmail.tequlia2pop.utils.Print.println;

/**
 * 使用 enum 构造了一个小型状态机，主要展示了在 switch 中使用 enum。
 * 
 * switch 语句适合于给外部的枚举类型增加特定于常量的行为。
 */

// 定义枚举类型。
enum Signal {
	GREEN, YELLOW, RED,
}

public class TrafficLight {
	Signal color = Signal.RED;

	public void change() {
		switch (color) {
		// 注意，你不必在 case 语句中编写 Signal.RED：
		case RED:
			color = Signal.GREEN;
			break;
		case GREEN:
			color = Signal.YELLOW;
			break;
		case YELLOW:
			color = Signal.RED;
			break;
		}
	}

	@Override
	public String toString() {
		return "The traffic light is " + color;
	}

	public static void main(String[] args) {
		TrafficLight t = new TrafficLight();
		for (int i = 0; i < 7; i++) {
			println(t);
			t.change();
		}
	}

}
