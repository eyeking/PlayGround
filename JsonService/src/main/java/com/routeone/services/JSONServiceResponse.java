package com.routeone.services;

public class JSONServiceResponse {
	private final int httpResponseCode;
	private final String responseBody;
	
	public JSONServiceResponse(int httpResponseCode, String responseBody) {
		this.httpResponseCode=httpResponseCode;
		this.responseBody=responseBody;
	}

	public int getHttpResponseCode() {
		return httpResponseCode;
	}

	public String getResponseBody() {
		return responseBody;
	}
	
}
