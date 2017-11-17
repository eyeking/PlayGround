package com.routeone.hmac;


import com.routeone.api.HmacAuthenticationGeneratorRequest;
import com.routeone.fault.HmacSignatureGenerationException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;


import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class HmacAuthenticationGenerator {

    public static String getAuthorizationHeaderValue(HmacAuthenticationGeneratorRequest request) throws
            HmacSignatureGenerationException {

        String canonicalRepresentation = new HmacCanonicalRepresentation(request).toString();

        return new HmacSignature(request.getSecretKey(), "HmacSHA256", canonicalRepresentation).compute();
    }

    public static String getMd5ForBytes(final byte[] bytes) {
        byte[] md5 = DigestUtils.md5(bytes);
        return new String(Base64.encodeBase64(md5));
    }

    public static String getMD5HeaderValue(String inData) {
        return getMd5ForBytes(inData.getBytes(Charset.forName("UTF-8")));
    }

    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return httpDateFormat.format(calendar.getTime());
    }

}