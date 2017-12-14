package com.gmail.tequlia2pop.java.showcases.exceptions.clean;

import com.gmail.tequlia2pop.java.showcases.exceptions.clean.Switch;

// 为什么要使用 finally？

public class OnOffSwitch {
	private static Switch sw = new Switch();

	public static void f() throws OnOffException1, OnOffException2 {
		throw new OnOffException2();
	}

	public static void main(String[] args) {
		try {
			sw.on();
			// Code that can throw exceptions...
			f();
		} catch (OnOffException1 e) {
			System.out.println("OnOffException1");
		} catch (OnOffException2 e) {
			System.out.println("OnOffException2");
		} finally {
			sw.off();
		}
	}
}