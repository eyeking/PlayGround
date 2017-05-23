package com.cosmicdust.corekan.api;

public abstract class Actor<T extends Parent> {
	public void actDependOnType(T parent)
	{
		System.out.println("From Actor.actDependOnType()");
	};

}
