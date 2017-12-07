package com.gmail.tequlia2pop.java.showcases.enumerated;

// 枚举类型：特定于常量的方法和数据。

import java.util.HashMap;
import java.util.Map;

public enum Operation {
	PLUS("+") {
		@Override
		double apply(double x, double y) {
			return x + y;
		}
	},
	MINUS("-") {
		@Override
		double apply(double x, double y) {
			return x - y;
		}
	},
	TIMES("*") {
		@Override
		double apply(double x, double y) {
			return x * y;
		}
	},
	DIVIDE("/") {
		@Override
		double apply(double x, double y) {
			return x / y;
		}
	};

	// 在枚举类型上实现一个 fromString()
	
	private static final Map<String, Operation> stringToEnum = new HashMap<>();

	// 初始化时，将常量名称映射到枚举常量。
	static {
		for (Operation op : values())
			stringToEnum.put(op.toString(), op);
	}

	// 返回字符串表示对应的操作，如果字符串无效，则返回 null。
	public static Operation fromString(String symbol) {
		return stringToEnum.get(symbol);
	}

	private final String symbol;

	Operation(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}

	abstract double apply(double x, double y);

	// 如果要在枚举中实现特定于常量的行为，switch 语句就不是一种很好的选择了。
	// 因为，如果添加了新的枚举常量，却忘了给 switch 添加相应的条件，枚举仍然可以编译，
	// 但是当你试图运用新的枚举常量时，就会运行失败。
	// 在这种情况下，更好的解决方法是使用特定于常量的方法实现。
	// switch 语句适合于给外部的枚举类型增加特定于常量的行为。
	/*double apply(double x, double y) {
		switch (this) {
		case PLUS:
			return x + y;
		case MINUS:
			return x - y;
		case TIMES:
			return x * y;
		case DIVIDE:
			return x / y;
		}
		throw new AssertionError("Unknown op: " + this);
	}*/

	// Test program to perform all operations on given operands
	public static void main(String[] args) {
		args = new String[] { "2", "4" };
		double x = Double.parseDouble(args[0]);
		double y = Double.parseDouble(args[1]);
		for (Operation op : Operation.values())
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
	}
}
