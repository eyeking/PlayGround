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























































/*
 * overload resolution happens at compile time, that is, the compiler decides
 * which overloaded version must be called. The compiler does so when the
 * generic method is translated to its unique byte code representation. During
 * that translation type erasure is performed, which means that type parameters
 * are replaced by their leftmost bound or Object if no bound was specified.
 * Consequently, the leftmost bound or Object determines which version of an
 * overloaded method is invoked. What type of object is passed to the method at
 * runtime is entirely irrelevant for overload resolution. ?
 */
