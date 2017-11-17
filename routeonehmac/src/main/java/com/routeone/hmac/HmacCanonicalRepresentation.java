package com.routeone.hmac;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.ListMultimap;
import com.google.common.net.HttpHeaders;
import com.routeone.api.HmacAuthenticationGeneratorRequest;
import com.routeone.api.RouteOneHeaders;


public class HmacCanonicalRepresentation {

	private static final String NEWLINE="\n";
	private static final String COLON=":";
	private static final String COMMA=",";

	private final String httpMethod;
	private final String requestPath;
	private final ListMultimap<String, String> requestHeaders;
	private final ListMultimap<String, String> queryParameters;

	public HmacCanonicalRepresentation(final HmacAuthenticationGeneratorRequest hmacAuthenticationRequest) {
		this.httpMethod = hmacAuthenticationRequest.getHttpRequestMethod();
		this.requestPath = hmacAuthenticationRequest.getRequestPath();
		this.requestHeaders = hmacAuthenticationRequest.getRequestHeaders();
		this.queryParameters = hmacAuthenticationRequest.getQueryParameters();
	}

	@Override
	public String toString() {
		final String contentMd5 = getFirst(requestHeaders, HttpHeaders.CONTENT_MD5);
		final String contentType = getFirst(requestHeaders, HttpHeaders.CONTENT_TYPE);
		final String date = requestHeaders.containsKey(RouteOneHeaders.X_ROUTEONE_DATE) ? requestHeaders.get(
				RouteOneHeaders.X_ROUTEONE_DATE).get(0) : requestHeaders.get(HttpHeaders.DATE).get(0);
		
		final StringBuilder canonicalRepresentation = new StringBuilder();
		canonicalRepresentation.append(httpMethod.toUpperCase()).append(NEWLINE);
		canonicalRepresentation.append(Strings.nullToEmpty(contentMd5).toLowerCase()).append(NEWLINE);
		canonicalRepresentation.append(Strings.nullToEmpty(contentType).toLowerCase()).append(NEWLINE);
		canonicalRepresentation.append(date.toLowerCase()).append(NEWLINE);
		canonicalRepresentation.append(getCanonicalizedHeaders()).append(NEWLINE);
		canonicalRepresentation.append(getCanonicalizedResource());
		
		return canonicalRepresentation.toString();
	}

	private String getCanonicalizedHeaders() {
		final Map<String, String> sortedCanonicalHeaders = new TreeMap<>();
		
		// Sort the headers by the lower cased key
		for (Entry<String, Collection<String>> header : requestHeaders.asMap().entrySet()) {
			final String headerName = header.getKey();
			if (headerName.startsWith(RouteOneHeaders.X_ROUTEONE_PREFIX)) {
				final String headerValue = Joiner.on(COMMA).join(header.getValue());
				sortedCanonicalHeaders.put(headerName.toLowerCase(), headerValue);
			}
		}
		
		// Append sorted canonical headers to string
		final StringBuilder canonicalHeaders = new StringBuilder();
		for (Entry<String, String> header : sortedCanonicalHeaders.entrySet()) {
			canonicalHeaders
				.append(header.getKey())
				.append(COLON)
				.append(header.getValue())
				.append(NEWLINE);
		}
		
		return canonicalHeaders.toString().trim();
	}

	private String getCanonicalizedResource() {
		final Map<String, String> sortedQueryParameters = new TreeMap<>();
		
		final StringBuilder canonicalResource = new StringBuilder();
		canonicalResource.append(requestPath).append(NEWLINE);

		final StringBuilder queryParamBuilder = new StringBuilder();
		if (queryParameters != null) {
			// Sort the query parameters by the lower cased key
			for (Entry<String, Collection<String>> queryParameter : queryParameters.asMap().entrySet()) {
				final String name = queryParameter.getKey().toLowerCase();
				final String value = Joiner.on(COMMA).join(queryParameter.getValue());
				sortedQueryParameters.put(name, value);
			}

			// Append sorted query parameters

			for (Entry<String, String> queryParameter : sortedQueryParameters.entrySet()) {
				queryParamBuilder.append(queryParameter.getKey())
								 .append(COLON)
								 .append(queryParameter.getValue())
								 .append(NEWLINE);
			}
		}
		return canonicalResource.append(queryParamBuilder.toString().trim()).toString();
	}

	private String getFirst(ListMultimap<String, String> headers, String key) {
		List<String> values = headers.get(key);
		if(values != null && !values.isEmpty()) {
			return values.get(0);
		}
		return null;
	}
}
