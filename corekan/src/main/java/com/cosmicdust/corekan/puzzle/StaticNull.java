package com.cosmicdust.corekan.puzzle;

public class StaticNull {
	private static int luckyNumber = 23;

	public static StaticNull getInstance() {
		return null;
	}

	public static void main(String[] args) {
		System.out.println(StaticNull.getInstance());
		System.out.println(StaticNull.getInstance().luckyNumber);
	}

}






































/*
 * a null reference may be used to access a class (static) variable without
 * causing an exception.
 */