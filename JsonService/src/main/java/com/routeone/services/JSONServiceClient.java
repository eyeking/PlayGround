package com.routeone.services;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class JSONServiceClient {
	private static final String HMAC_SHA256 = "HmacSHA256";
	private static final String X_ROUTEONE="X-RouteOne";
	private static final String X_ROUTEONE_DATE="X-RouteOne-Date";
	private static final String NEWLINE="\n";
	private static final String COLON=":";
	private static final String COMMA=",";
	private static final String EMPTY_STRING="";
	
	private final DefaultHttpClient httpclient = new DefaultHttpClient();

	private final String host;
	private final int port;

	private final String authenticationID; //HTTP Basic Auth Username or HMAC-SHA256 API User ID
	private final String authenticationSecret; //HTTP Basic Auth Password or HMAC-SHA256 Shared Secret

	private final boolean useSSL;
	private final boolean secured;
	private final boolean basicAuth;
	
	
	
	public static JSONServiceClient basicAuth(String host, int port, String basicAuthUser, String basicAuthPassword, boolean useSSL) {
		return new JSONServiceClient(host,port,basicAuthUser,basicAuthPassword,true,true,useSSL);
	}
	
	public static JSONServiceClient hmacAuth(String host, int port, String hmacUserId, String hmacSharedSecret, boolean useSSL) {
		return new JSONServiceClient(host,port,hmacUserId,hmacSharedSecret,true,false, useSSL);
	}
	
	public static JSONServiceClient unauthenticated(String host, int port, boolean useSSL) {
		return new JSONServiceClient(host,port,null,null,false,false, useSSL);
	}
	
	private JSONServiceClient(String host, int port, String authenticationID, String authenticationSecret, boolean secured, boolean basicAuth, boolean useSSL) {
		this.host=host;
		this.port=port;
		this.authenticationID=authenticationID;
		this.authenticationSecret=authenticationSecret;
		this.secured=secured;
		this.basicAuth=basicAuth;
		this.useSSL=useSSL;
		
		if(this.secured && this.basicAuth) {
			httpclient.getCredentialsProvider().setCredentials(new AuthScope(this.host, this.port), new UsernamePasswordCredentials(this.authenticationID, this.authenticationSecret));
		}
	}	

	public JSONServiceResponse GET(String path, Map<String,String> queryParameters, Map<String,String> headers) throws JSONServiceClientException {
		return httpMethodCall(path, queryParameters, headers, null, null, new HttpGet());
	}
	
	public JSONServiceResponse POST(String path, Map<String,String> queryParameters, Map<String,String> headers, String body, String bodyContentType) throws JSONServiceClientException {
		return httpMethodCall(path, queryParameters, headers, body, bodyContentType, new HttpPost());
	}

	public JSONServiceResponse PUT(String path, Map<String,String> queryParameters, Map<String,String> headers, String body, String bodyContentType) throws JSONServiceClientException {
		return httpMethodCall(path, queryParameters, headers, body, bodyContentType, new HttpPut());
	}
	
	public JSONServiceResponse DELETE(String path, Map<String,String> queryParameters, Map<String,String> headers) throws JSONServiceClientException {
		return httpMethodCall(path, queryParameters, headers, null, null, new HttpDelete());	
	}
	
	private JSONServiceResponse httpMethodCall(String path, Map<String,String> queryParameters, Map<String,String> headers, String body, String bodyContentType, HttpRequestBase method) throws JSONServiceClientException {
		method.setURI(getURI(path,queryParameters));

		addHeaders(method, headers);
		
		if(body!=null && method instanceof HttpEntityEnclosingRequestBase) {
			addBody((HttpEntityEnclosingRequestBase)method,body,bodyContentType);
		}

		if(this.secured && !this.basicAuth) {
			addAuthorizationHeader(method);
		}
		
		return callService(method);
	}
	
	private URI getURI(String path, Map<String,String> queryParameters) throws JSONServiceClientException {
		StringBuilder uriString = new StringBuilder(this.useSSL ?  "https://" : "http://");
		uriString.append(this.host);
		uriString.append(":");
		uriString.append(this.port);
		uriString.append(path);

		if(queryParameters!=null && !queryParameters.isEmpty()) {
			Iterator<String> queryParamIter = queryParameters.keySet().iterator();
			String queryParam;
			uriString.append("?");
			while(queryParamIter.hasNext()) {
				queryParam=queryParamIter.next();
				uriString.append(queryParam);
				uriString.append("=");
				uriString.append(queryParameters.get(queryParam));
				if(queryParamIter.hasNext()) {
					uriString.append("&");
				}
			}
		}
		URI uri=null;
		try {
			uri = new URI(uriString.toString());
		} catch (URISyntaxException e) {
			throw new JSONServiceClientException("Unable to generate valid URI for request", e);
		}
		return uri;
	}
	
	private void addAuthorizationHeader(HttpRequestBase method) throws JSONServiceClientException {
		String contentMd5=getHeaderValue(method, "Content-MD5");
		String contentType=getHeaderValue(method, "Content-Type");

		String date = getHeaderValue(method, X_ROUTEONE_DATE);
		if(date==null) {
			date = getHeaderValue(method, "Date");
			if(date==null) {
				date=getDate();
				method.addHeader("Date",date);
			}
		}
		
		final StringBuilder canonicalRepresentation = new StringBuilder();
		canonicalRepresentation.append(method.getMethod().toUpperCase()).append(NEWLINE);
		canonicalRepresentation.append(contentMd5==null ? EMPTY_STRING : contentMd5.toLowerCase()).append(NEWLINE);
		canonicalRepresentation.append(contentType==null ? EMPTY_STRING :contentType.toLowerCase()).append(NEWLINE);
		canonicalRepresentation.append(date.toLowerCase()).append(NEWLINE);
		canonicalRepresentation.append(getCanonicalizedHeaders(method)).append(NEWLINE);
		canonicalRepresentation.append(getCanonicalizedResource(method));
		String sig="";
		try {
			SecretKey secretKey = new SecretKeySpec(authenticationSecret.getBytes(), HMAC_SHA256);
			Mac mac = Mac.getInstance(HMAC_SHA256);
			mac.init(secretKey);
			mac.update(canonicalRepresentation.toString().getBytes());
			sig= new String( Base64.encodeBase64(mac.doFinal()) );
		} catch (NoSuchAlgorithmException e) {
			throw new JSONServiceClientException("Could not find HMAC_SHA256 Implementation",e);
		} catch (InvalidKeyException e) {
			throw new JSONServiceClientException("invalid secret key",e);
		}
		String authHdrval = "RouteOne "+authenticationID+":"+sig;
		method.addHeader("Authorization", authHdrval);		
	}
	
	private String getHeaderValue(HttpRequestBase method, String headerName) {
		Header h = method.getFirstHeader(headerName);
		if(h!=null) {
			return h.getValue();
		}
		return null;
	}
	
	private String getDate() {
	    Calendar calendar = Calendar.getInstance();
		SimpleDateFormat httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
	    httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	    return httpDateFormat.format(calendar.getTime());
	}
	
	
	private String getCanonicalizedHeaders(HttpRequestBase method) {
		final Map<String, String> sortedCanonicalHeaders = new TreeMap<String, String>();
		Header[] allHeaders = method.getAllHeaders();
		// Sort the headers by the lower cased key
		for (Header header : allHeaders) {
			final String headerName = header.getName();
			if (headerName.startsWith(X_ROUTEONE)) {
				Header[] r1HeaderVals = method.getHeaders(headerName);
				Set<String> vals = new HashSet<String>(r1HeaderVals.length);
				for(int i=0;i<r1HeaderVals.length;i++) {
					vals.add(r1HeaderVals[i].getValue());
				}
				final String headerValue = org.apache.commons.lang.StringUtils.join(vals,COMMA);
				sortedCanonicalHeaders.put(headerName.toLowerCase(), headerValue);
			}
		}
		
		// Append sorted canonical headers to string
		final StringBuilder canonicalHeaders = new StringBuilder(EMPTY_STRING);
		for (Entry<String, String> header : sortedCanonicalHeaders.entrySet()) {
			canonicalHeaders
				.append(header.getKey())
				.append(COLON)
				.append(header.getValue())
				.append(NEWLINE);
		}
		
		return canonicalHeaders.toString().trim();
	}

	private String getCanonicalizedResource(HttpRequestBase method) {
		Map<String, String> sortedQueryParameters = new TreeMap<String, String>();
		URI uri = method.getURI();
		StringBuilder canonicalResource = new StringBuilder(200);
		canonicalResource.append(uri.getPath());
		canonicalResource.append(NEWLINE);
		
		if(uri.getRawQuery()!=null) {
			String[] queryparams=uri.getRawQuery().split("&");
			// Sort the query parameters by the lower cased key
			String[] nameValue=null;
			String tVal=null;
			for (String queryParameter : queryparams) {
				nameValue=queryParameter.split("=");
				String name = nameValue[0].toLowerCase();
				String value = nameValue[1];
				if(sortedQueryParameters.containsKey(name)) {
					tVal=sortedQueryParameters.get(name);
					tVal=tVal+COMMA+value;
					value=tVal;
				}
				sortedQueryParameters.put(name, value);
			}
			
			// Append sorted query parameters
			StringBuilder queryParameters = new StringBuilder();
			for (Entry<String, String> queryParameter : sortedQueryParameters.entrySet()) {
				queryParameters.append(queryParameter.getKey()).append(COLON).append(queryParameter.getValue()).append(NEWLINE);
			}
			
			canonicalResource.append(queryParameters.toString().trim());
		}
		return canonicalResource.toString();
	}	
	
	private void addHeaders(HttpRequestBase method, Map<String,String> headers) {
		if(headers!=null && !headers.isEmpty()) {
			Iterator<String> headerIter = headers.keySet().iterator();
			String header;
			while(headerIter.hasNext()) {
				header=headerIter.next();
				method.setHeader(header, headers.get(header));
			}
		}
	}

	private void addBody(HttpEntityEnclosingRequestBase method, String body, String bodyContentType) throws JSONServiceClientException {
		if(body!=null) { 
			BasicHttpEntity entity = new BasicHttpEntity();
			entity.setContent(IOUtils.toInputStream(body,Charsets.UTF_8));
			entity.setContentLength(body.length());
			try {
			method.setEntity(new BufferedHttpEntity(entity));
			} catch (IOException e) {
				throw new JSONServiceClientException("Unable to set request body", e);
			}
			method.setHeader("Content-MD5",md5(body));
			if(bodyContentType!=null && !bodyContentType.isEmpty()) {
				method.setHeader("Content-Type",bodyContentType);
			}
		}
	}
	
	private JSONServiceResponse callService(HttpRequestBase method) throws JSONServiceClientException {
		HttpResponse r = null;
		int httpResponseCode = -1;
		String responseBody = null;
		try {
			r= httpclient.execute(method);
			httpResponseCode = r.getStatusLine().getStatusCode();
			responseBody = readBody(r.getEntity());
		} catch(Exception e) {
			throw new JSONServiceClientException("Failed to execute request", e);
		} finally {
			method.reset();
		}
		
		return new JSONServiceResponse(httpResponseCode, responseBody);
	}
	
	private String readBody(HttpEntity response) throws JSONServiceClientException {
		StringWriter writer = new StringWriter();
		try {
			if(response.getContentEncoding()!=null) {
				IOUtils.copy(response.getContent(), writer, response.getContentEncoding().getValue());
			} else {
				IOUtils.copy(response.getContent(), writer);
			}
		} catch (IOException e) {
			throw new JSONServiceClientException("Error reading response body", e);
		}
		return writer.toString();
	}
	
	
	private String md5(String in) throws JSONServiceClientException {
		try  {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] computedMD5 = md.digest(in.getBytes());
			String computedMD5String = new String(Base64.encodeBase64(computedMD5));
			return computedMD5String;
		} catch(NoSuchAlgorithmException e) {
			throw new JSONServiceClientException("Unable to compute MD5 signature", e);
		}
	}

	public void shutdown() {
		httpclient.getConnectionManager().shutdown();
	}
}
