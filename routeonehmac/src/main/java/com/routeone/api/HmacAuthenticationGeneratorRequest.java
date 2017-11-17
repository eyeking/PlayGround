package com.routeone.api;

import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.net.HttpHeaders;

public class HmacAuthenticationGeneratorRequest {
    private String secretKey;
    private String rteOneDate;
    private String date;
    private String userId;
    private String rteOneDealerId;
    private String partnerDealerId;
    private String partnerUserId;
    private String httpRequestMethod;
    private String requestPath;
    private String contentMd5;
    private String contentType;
    private ListMultimap<String, String> requestHeaders;
    private ListMultimap<String, String> queryParameters;

    private HmacAuthenticationGeneratorRequest(String secretKey,
                                               String rteOneDate,
                                               String date,
                                               String userId,
                                               String partnerUserId,
                                               String rteOneDealerId,
                                               String partnerDealerId,
                                               String httpRequestMethod,
                                               String requestPath,
                                               String contentMd5,
                                               String contentType,
                                               ListMultimap<String, String> requestHeaders,
                                               ListMultimap<String, String> queryParameters) {
        this.secretKey = secretKey;
        this.rteOneDate = rteOneDate;
        this.date = date;
        this.userId = userId;
        this.partnerUserId = partnerUserId;
        this.rteOneDealerId = rteOneDealerId;
        this.partnerDealerId = partnerDealerId;
        this.httpRequestMethod = httpRequestMethod;
        this.requestPath = requestPath;
        this.contentMd5 = contentMd5;
        this.contentType = contentType;
        this.requestHeaders = requestHeaders;
        this.queryParameters = queryParameters;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getRteOneDate() {
        return rteOneDate;
    }

    public String getDate() {
        return date;
    }

    public String getUserId() {
        return userId;
    }

    public String getPartnerUserId() {
        return partnerUserId;
    }

    public String getRteOneDealerId() {
        return rteOneDealerId;
    }

    public String getPartnerDealerId() {
        return partnerDealerId;
    }

    public String getHttpRequestMethod() {
        return httpRequestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public String getContentMd5() {
        return contentMd5;
    }

    public String getContentType() {
        return contentType;
    }

    public ListMultimap<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public ListMultimap<String, String> getQueryParameters() {
        return queryParameters;
    }

    public static class Builder {
        private String secretKey;
        private String rteOneDate;
        private String date;
        private String userId;
        private String partnerUserId;
        private String rteOneDealerId;
        private String partnerDealerId;
        private String httpRequestMethod;
        private String requestPath;
        private String contentMd5;
        private String contentType;
        private ListMultimap<String, String> requestHeaders = ArrayListMultimap.create();
        private ListMultimap<String, String> queryParameters;

        public Builder(String secretKey) {
            this.secretKey = secretKey;
        }

        public Builder withContentMd5(String contentMd5) {
            this.contentMd5 = contentMd5;
            putHeaderIfValueNotNullOrEmpty(HttpHeaders.CONTENT_MD5, contentMd5);
            return this;
        }

        public Builder withRteOneDate(String rteOneDate) {
            this.rteOneDate = rteOneDate;
            putHeaderIfValueNotNullOrEmpty(RouteOneHeaders.X_ROUTEONE_DATE, rteOneDate);
            return this;
        }

        public Builder withdate(String date) {
            this.date = date;
            putHeaderIfValueNotNullOrEmpty(HttpHeaders.DATE, date);
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            putHeaderIfValueNotNullOrEmpty(RouteOneHeaders.USER_ID, userId);
            return this;
        }

        public Builder withPartnerUserId(String partnerUserId) {
            this.partnerUserId = partnerUserId;
            putHeaderIfValueNotNullOrEmpty(RouteOneHeaders.PARTNER_USER_ID, partnerUserId);
            return this;
        }

        public Builder withRteOneDealerId(String rteOneDealerId) {
            this.rteOneDealerId = rteOneDealerId;
            putHeaderIfValueNotNullOrEmpty(RouteOneHeaders.DEALER_ID, rteOneDealerId);
            return this;
        }

        public Builder withPartnerDealerId(String partnerDealerId) {
            this.partnerDealerId = partnerDealerId;
            putHeaderIfValueNotNullOrEmpty(RouteOneHeaders.PARTNER_DEALER_ID, partnerDealerId);
            return this;
        }

        public Builder withHttpRequestMethod(String httpRequestMethod) {
            this.httpRequestMethod = httpRequestMethod;
            return this;
        }

        public Builder withRequestPath(String requestPath) {
            this.requestPath = requestPath;
            return this;
        }

        public Builder withContentType(String contentType) {
            this.contentType = contentType;
            putHeaderIfValueNotNullOrEmpty(HttpHeaders.CONTENT_TYPE, contentType);
            return this;
        }

        public Builder withQueryParameters(ListMultimap<String, String> queryParameters) {
            this.queryParameters = queryParameters;
            return this;
        }

        public HmacAuthenticationGeneratorRequest create() {
            return new HmacAuthenticationGeneratorRequest(secretKey, rteOneDate, date, userId, partnerUserId, rteOneDealerId, partnerDealerId,
                                                          httpRequestMethod, requestPath, contentMd5, contentType, requestHeaders, queryParameters);
        }

        private void putHeaderIfValueNotNullOrEmpty(String key, String value) {
            if(!Strings.isNullOrEmpty(value)) {
                requestHeaders.put(key, value);
            }
        }
    }
}
