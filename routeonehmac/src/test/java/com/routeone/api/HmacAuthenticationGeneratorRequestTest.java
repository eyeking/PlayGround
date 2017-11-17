package com.routeone.api;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;

import org.junit.Test;

import com.google.common.net.HttpHeaders;

public class HmacAuthenticationGeneratorRequestTest {
    public static final String API_SHARED_SECRET = "a1b2c3d4e5f6g7h8i9j0";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String R1_DEALER_ID = "D12345";
    public static final String PARTNER_DEALER_ID = "40506";
    public static final String CANONICAL_DATE = "thu, 04 feb 2016 16:29:02 gmt";
    public static final String USER_ID = "JOHNDOE";
    public static final String CONTENT_MD5 = "c5XhceQSbvLvn9FkWoCcog==";
    public static final String HTTP_REQUEST_METHOD = "POST";
    public static final String ROUTEONE_URL_PATH = "/customer-quote/finance";

    public static final HmacAuthenticationGeneratorRequest HMAC_GENERATOR_REQUEST_FROM_PARAMS;

    static {
        HMAC_GENERATOR_REQUEST_FROM_PARAMS = new HmacAuthenticationGeneratorRequest.Builder(
                API_SHARED_SECRET).withHttpRequestMethod(HTTP_REQUEST_METHOD)
                                  .withRequestPath(ROUTEONE_URL_PATH)
                                  .withRteOneDealerId(R1_DEALER_ID)
                                  .withPartnerDealerId(PARTNER_DEALER_ID)
                                  .withRteOneDate(CANONICAL_DATE)
                                  .withUserId(USER_ID)
                                  .withContentMd5(CONTENT_MD5)
                                  .withContentType(CONTENT_TYPE_JSON)
                                  .create();
    }

    @Test
    public void validRequestFromParameters() {
        String requestURI = HMAC_GENERATOR_REQUEST_FROM_PARAMS.getRequestPath();

        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getSecretKey(), is(API_SHARED_SECRET));
        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getContentMd5(), is(CONTENT_MD5));
        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getContentType(), is(CONTENT_TYPE_JSON));
        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getHttpRequestMethod(), is(HTTP_REQUEST_METHOD));
        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getPartnerDealerId(), is(PARTNER_DEALER_ID));
        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getUserId(), is(USER_ID));
        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getRteOneDealerId(), is(R1_DEALER_ID));
        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getRteOneDate(), is(CANONICAL_DATE));
        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getRequestPath(), is(ROUTEONE_URL_PATH));

        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getRequestHeaders().get(RouteOneHeaders.DEALER_ID).get(0), is(R1_DEALER_ID));
        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getRequestHeaders().get(RouteOneHeaders.PARTNER_DEALER_ID).get(0), is(PARTNER_DEALER_ID));
        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getRequestHeaders().get(RouteOneHeaders.X_ROUTEONE_DATE).get(0), is(CANONICAL_DATE));
        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getRequestHeaders().get(RouteOneHeaders.USER_ID).get(0), is(USER_ID));
        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getRequestHeaders().get(HttpHeaders.CONTENT_MD5).get(0), is(CONTENT_MD5));
        assertThat(HMAC_GENERATOR_REQUEST_FROM_PARAMS.getRequestHeaders().get(HttpHeaders.CONTENT_TYPE).get(0), is(CONTENT_TYPE_JSON));
    }
}