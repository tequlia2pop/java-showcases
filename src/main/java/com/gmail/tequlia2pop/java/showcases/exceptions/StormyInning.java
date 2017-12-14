package com.gmail.tequlia2pop.java.showcases.exceptions;

// 覆盖方法可能只会抛出在其基类版本中指定的异常，或者从基类异常导出的异常。

// 棒球的异常。
class BaseballException extends Exception {
}

// 犯规的异常。
class Foul extends BaseballException {
}

// 打人的异常。
class Strike extends BaseballException {
}

//
class PopFoul extends Foul {
}

// 一局棒球。
abstract class Inning {
	public Inning() throws BaseballException {
	}

	public void event() throws BaseballException {
		// 实际上不会抛出任何异常
	}

	// 击球。
	public abstract void atBat() throws Strike, Foul;

	public void walk() {
	} // Throws no checked exceptions
}

// 风暴的异常。
class StormException extends Exception {
}

// 下雨的异常。
class RainedOut extends StormException {
}

interface Storm {
	// 包含了在 Inning 中定义的方法 event()
	public void event() throws RainedOut;

	public void rainHard() throws RainedOut;
}

// 风暴中的一局棒球。
public class StormyInning extends Inning implements Storm {
	// 可以为构造函数添加新的异常，但你必须处理基类构造函数的异常：
	public StormyInning() throws RainedOut, BaseballException {
	}

	public StormyInning(String s) throws Foul, BaseballException {
	}

	// 常规方法必须符合基类：
	//! void walk() throws PopFoul {} //Compile error

	// 接口无法向覆盖自基类的现有方法添加异常：
	//! public void event() throws RainedOut {}

	// 如果方法在基类中不存在，那么可以抛出这个异常：
	public void rainHard() throws RainedOut {
	}

	// 即使基类抛出了一个异常，在这里你也可以选择不抛出任何异常：
	public void event() {
	}

	// 覆盖的方法可以抛出继承的异常：
	public void atBat() throws PopFoul {
	}

	public static void main(String[] args) {
		try {
			StormyInning si = new StormyInning();
			si.atBat();
		} catch (PopFoul e) {
			System.out.println("Pop foul");
		} catch (RainedOut e) {
			System.out.println("Rained out");
		} catch (BaseballException e) {
			System.out.println("Generic baseball exception");
		}

		// Strike not thrown in derived version.
		try {
			// What happens if you upcast?
			Inning i = new StormyInning();
			i.atBat();
			// You must catch the exceptions from the
			// base-class version of the method:
		} catch (Strike e) {
			System.out.println("Strike");
		} catch (Foul e) {
			System.out.println("Foul");
		} catch (RainedOut e) {
			System.out.println("Rained out");
		} catch (BaseballException e) {
			System.out.println("Generic baseball exception");
		}
	}
}
