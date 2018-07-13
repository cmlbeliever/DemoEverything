package com.cml.request.distribute;

import java.util.Map;

public class RequestArgs {
    private final String url;
    private final String requestBody;
    private final Map header;

    public RequestArgs(String url, String requestBody, Map header) {
        this.url = url;
        this.requestBody = requestBody;
        this.header = header;
    }

    public String getUrl() {
        return url;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public Map getHeader() {
        return header;
    }
}
