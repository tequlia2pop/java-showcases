package com.gmail.tequlia2pop.java.showcases.exceptions.clean;

import static com.gmail.tequlia2pop.utils.Print.println;

public class Switch {
	private boolean state = false;

	public boolean read() {
		return state;
	}

	public void on() {
		state = true;
		println(this);
	}

	public void off() {
		state = false;
		println(this);
	}

	@Override
	public String toString() {
		return state ? "on" : "off";
	}
}
