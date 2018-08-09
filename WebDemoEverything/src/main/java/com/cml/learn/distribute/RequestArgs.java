package com.cml.learn.distribute;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestArgs {
    private String url;
    private String requestBody;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain chain;

    public void doFilter() throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public FilterChain getChain() {
        return chain;
    }

    public void setChain(FilterChain chain) {
        this.chain = chain;
    }
}
