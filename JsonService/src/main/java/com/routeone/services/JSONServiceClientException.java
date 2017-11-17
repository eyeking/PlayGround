package com.routeone.services;

public class JSONServiceClientException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public JSONServiceClientException(String msg, Exception e) {
		super(msg,e);
	}

}
