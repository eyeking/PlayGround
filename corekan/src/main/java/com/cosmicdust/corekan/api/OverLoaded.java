package com.cosmicdust.corekan.api;

public class OverLoaded {
	public static void printSomething(Child1 child1)
	{
		System.out.println("child1 parameter");
		
	}
	public static void printSomething(Child2 child2)
	{
		System.out.println("child2 parameter");
	}
	
	public static void printSomething(ParentImpl parent)
	{
		System.out.println("Parent parameter");
	}

}
