package com.cosmicdust.corekan.api;

public abstract class ParentImpl implements Parent{

	@Override
	public Parent identify() {
		return this;
	}

	public static void whatPrints(Address address) {
		System.out.println("from ParentImpl");
	}
}
