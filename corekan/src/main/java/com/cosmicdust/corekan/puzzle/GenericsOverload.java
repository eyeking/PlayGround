package com.cosmicdust.corekan.puzzle;

public class GenericsOverload {
	static void overloadedMethod(Object o) {
		System.out.println("overloadedMethod(Object) called");
	}

	static void overloadedMethod(String s) {
		System.out.println("overloadedMethod(String) called");
	}

	static void overloadedMethod(Integer i) {
		System.out.println("overloadedMethod(Integer) called");
	}

	static <T> void genericMethod(T t) {
		overloadedMethod(t);
	}

	public static void main(String[] args) {
		genericMethod("abc");
	}

}
