package com.routeone.hmac;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.routeone.fault.HmacSignatureGenerationException;
import org.apache.commons.codec.binary.Base64;


public class HmacSignature {
	private static final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");

	private String sharedSecret;
	private String algorithm;
	private String canonicalRepresentation;

	public HmacSignature(String sharedSecret, String algorithm, String canonicalRepresentation) {
		this.sharedSecret = sharedSecret;
		this.algorithm = algorithm;
		this.canonicalRepresentation = canonicalRepresentation;
	}
	
	public String compute() throws HmacSignatureGenerationException {
		try {
			SecretKey secretKey = new SecretKeySpec(sharedSecret.getBytes(CHARSET_UTF_8), algorithm);
			Mac mac = Mac.getInstance(algorithm);
			mac.init(secretKey);
			mac.update(canonicalRepresentation.getBytes(CHARSET_UTF_8));
			return new String(Base64.encodeBase64(mac.doFinal()));
		} catch (NoSuchAlgorithmException | InvalidKeyException ex) {
			throw new HmacSignatureGenerationException("Error computing hmac signature. \n", ex);
		}
	}
}
