package com.cosmicdust.corekan.api;

public class ActorImpl2 extends Actor<Child2>{

	@Override
	public void actDependOnType(Child2 p) {
		System.out.println("From ActorImpl2.actDependOnType()");
	}


}