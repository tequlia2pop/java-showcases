package com.gmail.tequlia2pop.java.showcases.exceptions.clean;

// 每个要清理的对象必须跟随一个 try-finally

class NeedsCleanup { // 构造不会失败
	private static long counter = 1;
	private final long id = counter++;

	public void dispose() {
		System.out.println("NeedsCleanup " + id + " disposed");
	}
}

class ConstructionException extends Exception {
	private static final long serialVersionUID = 8505343907529579918L;
}

class NeedsCleanup2 extends NeedsCleanup {
	// 构造可能失败：
	public NeedsCleanup2() throws ConstructionException {
	}
}

public class CleanupIdiom {
	public static void main(String[] args) {
		// Section 1:
		// 如果对象构造不会失败，就不需要任何 catch：
		NeedsCleanup nc1 = new NeedsCleanup();
		try {
			// ...
		} finally {
			nc1.dispose();
		}

		// Section 2:
		// 对于构造不会失败的对象，可以组合在一起：
		NeedsCleanup nc2 = new NeedsCleanup();
		NeedsCleanup nc3 = new NeedsCleanup();
		try {
			// ...
		} finally {
			nc3.dispose(); // 构造的反序
			nc2.dispose();
		}

		// Section 3:
		// 如果构造可能失败，对于每一个构造都必须包含在其自己的 try-finally 语句块中：
		try {
			NeedsCleanup2 nc4 = new NeedsCleanup2();
			try {
				NeedsCleanup2 nc5 = new NeedsCleanup2();
				try {
					// ...
				} finally {
					nc5.dispose();
				}
			} catch (ConstructionException e) { // nc5 constructor
				System.out.println(e);
			} finally {
				nc4.dispose();
			}
		} catch (ConstructionException e) { // nc4 constructor
			System.out.println(e);
		}
	}
}