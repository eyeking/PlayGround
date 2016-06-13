package com.cosmicdust.corekan.puzzle;

public class TypePromotion {
	public static void main(String[] args) {
		int x = 5;
		
		Integer i = Integer.MIN_VALUE;
		if (x == 5)
		    i = null;
		
		Double d = new Double(2.0);
		Object o = x==5 ? i : d; 
		
		System.out.println(o);
	}
}
