package com.cosmicdust.corekan.api;

public class ActorImpl1 extends Actor<Child1>{

	@Override
	public void actDependOnType(Child1 p) {
		System.out.println("From ActorImpl1.actDependOnType()");
	}


}
