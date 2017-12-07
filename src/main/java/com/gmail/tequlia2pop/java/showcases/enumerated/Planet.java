package com.gmail.tequlia2pop.java.showcases.enumerated;

/**
 * 具有数据和行为的枚举类型。
 * 
 * 太阳系中的8颗行星都有质量和半径，通过这两个水星可以计算出它的表面重力。
 * 从而给定物体的质量，就可以计算出一个物体在行星表面上的重量。
 */
public enum Planet {
	MERCURY(3.302e+23, 2.439e6), //
	VENUS(4.869e+24, 6.052e6), //
	EARTH(5.975e+24, 6.378e6), //
	MARS(6.419e+23, 3.393e6), //
	JUPITER(1.899e+27, 7.149e7), //
	SATURN(5.685e+26, 6.027e7), //
	URANUS(8.683e+25, 2.556e7), //
	NEPTUNE(1.024e+26, 2.477e7);

	private final double mass; // 行星的质量（千克）
	private final double radius; // 行星的半径（米）
	private final double surfaceGravity; // 行星表面的重力，In m / s^2

	// Universal gravitational constant in m^3 / kg s^2
	private static final double G = 6.67300E-11;

	// 构造器
	Planet(double mass, double radius) {
		this.mass = mass;
		this.radius = radius;
		surfaceGravity = G * mass / (radius * radius);
	}

	public double mass() {
		return mass;
	}

	public double radius() {
		return radius;
	}

	public double surfaceGravity() {
		return surfaceGravity;
	}

	/**
	 * 返回给定的质量在该枚举常量所表示的行星上的重量。
	 * 
	 * @param mass
	 * @return
	 */
	public double surfaceWeight(double mass) {
		return mass * surfaceGravity; // F = ma
	}

	public static void main(String[] args) {
		args = new String[] { "175" };
		double earthWeight = Double.parseDouble(args[0]);
		double mass = earthWeight / Planet.EARTH.surfaceGravity();
		for (Planet p : Planet.values())
			System.out.printf("Weight on %s is %f%n", p, p.surfaceWeight(mass));
	}
}
