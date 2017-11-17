package com.routeone.hmac;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.routeone.api.HmacAuthenticationGeneratorRequest;
import com.routeone.api.HmacAuthenticationGeneratorRequestTest;
import org.junit.Test;


public class HmacAuthenticationGeneratorTest {
    public static final String AUTHORIZATION_DIGEST = "2ldzFe/jdWm38A1SsLMZmGlefhyeOUzNVOUabdR5mo4=";
    public static final String AUTHORIZATION_DIGEST_WITH_PARTNER_DEALER_ID ="yR5GlxNiKs8D7VvzInenG0TgplCkpnXi0M4V/E3Aj/Y=";

    @Test
    public void validAuthorizationDigestFromParams() throws Exception {

        HmacAuthenticationGeneratorRequest request = new HmacAuthenticationGeneratorRequest.Builder(
                HmacAuthenticationGeneratorRequestTest.API_SHARED_SECRET)
                .withHttpRequestMethod(HmacAuthenticationGeneratorRequestTest.HTTP_REQUEST_METHOD)
                .withRequestPath(HmacAuthenticationGeneratorRequestTest.ROUTEONE_URL_PATH)
                .withRteOneDealerId(HmacAuthenticationGeneratorRequestTest.R1_DEALER_ID)
                .withRteOneDate(HmacAuthenticationGeneratorRequestTest.CANONICAL_DATE)
                .withUserId(HmacAuthenticationGeneratorRequestTest.USER_ID)
                .withContentMd5(HmacAuthenticationGeneratorRequestTest.CONTENT_MD5)
                .withContentType(HmacAuthenticationGeneratorRequestTest.CONTENT_TYPE_JSON)
                .create();

        String authorizedDigest = HmacAuthenticationGenerator.getAuthorizationHeaderValue(request);

        assertThat(authorizedDigest, is(AUTHORIZATION_DIGEST));
    }

    @Test
    public void validAuthorizationDigestFromRequestHeader() throws Exception {
        String authorization = HmacAuthenticationGenerator.getAuthorizationHeaderValue(
                HmacAuthenticationGeneratorRequestTest.HMAC_GENERATOR_REQUEST_FROM_PARAMS);

        assertThat(authorization, is(AUTHORIZATION_DIGEST_WITH_PARTNER_DEALER_ID));
    }
}