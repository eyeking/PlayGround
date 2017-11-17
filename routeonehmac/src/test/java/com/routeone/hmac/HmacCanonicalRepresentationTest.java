package com.routeone.hmac;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;

import com.routeone.api.HmacAuthenticationGeneratorRequest;

import org.junit.Test;

public class HmacCanonicalRepresentationTest {
    public static final String API_SHARED_SECRET = "a1b2c3d4e5f6g7h8i9j0";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String R1_DEALER_ID = "D12345";
    private static final String CANONICAL_DATE = "thu, 04 feb 2016 16:29:02 gmt";
    private static final String USER_ID = "JOHNDOE";
    private static final String CONTENT_MD5 = "c5XhceQSbvLvn9FkWoCcog==";
    private static final String HTTP_REQUEST_METHOD = "POST";
    private static final String ROUTEONE_URL_PATH = "/customer-quote/finance";

    private static final String CANONICAL_REPRESENTATION = new StringBuilder().append(HTTP_REQUEST_METHOD).append("\n")
            .append(CONTENT_MD5.toLowerCase()).append("\n")
            .append(CONTENT_TYPE_JSON).append("\n")
            .append(CANONICAL_DATE).append("\n")
            .append("x-routeone-act-as-dealership:").append(R1_DEALER_ID).append("\n")
            .append("x-routeone-date:").append(CANONICAL_DATE).append("\n")
            .append("x-routeone-user-id:").append(USER_ID).append("\n")
            .append("/customer-quote/finance").append("\n").toString();

    private static HmacAuthenticationGeneratorRequest request;

    @Test
    public void validHmacCanonicalRepresentationFromParameters() {

        HmacAuthenticationGeneratorRequest request = new HmacAuthenticationGeneratorRequest.Builder(
                API_SHARED_SECRET).withHttpRequestMethod(HTTP_REQUEST_METHOD)
                                  .withRequestPath(ROUTEONE_URL_PATH)
                                  .withRteOneDealerId(R1_DEALER_ID)
                                  .withRteOneDate(CANONICAL_DATE)
                                  .withUserId(USER_ID)
                                  .withContentMd5(CONTENT_MD5)
                                  .withContentType(CONTENT_TYPE_JSON)
                                  .create();

        String canonicalRepresentation = new HmacCanonicalRepresentation(request).toString();

        assertThat(canonicalRepresentation, is(CANONICAL_REPRESENTATION));
    }
}