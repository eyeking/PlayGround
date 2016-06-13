package com.cosmicdust.corekan.puzzle;

public class StaticNull {

	static int luckyNumber = 23;

	public static StaticNull getInstance() {
		return null;
	}

	public static void main(String[] args) {

		System.out.println(StaticNull.getInstance().luckyNumber);
		System.out.println(StaticNull.getInstance().getInstance());

	}

}
	