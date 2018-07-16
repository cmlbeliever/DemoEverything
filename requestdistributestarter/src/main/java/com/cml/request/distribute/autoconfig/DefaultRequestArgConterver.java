package com.cml.request.distribute.autoconfig;

import org.apache.commons.io.IOUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultRequestArgConterver implements RequestArgConverter<RequestArgs> {

    private String encoding = "UTF-8";
    private boolean decodeRequestBody = true;

    @Override
    public RequestArgs convert(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        RequestArgs requestArgs = new RequestArgs();
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        if (decodeRequestBody) {
            int len = servletRequest.getContentLength();
            if (len > 0) {
                byte[] content = new byte[len];
                IOUtils.readFully(servletRequest.getInputStream(), content);
                requestArgs.setRequestBody(new String(content, encoding));
            }
        }
        requestArgs.setRequest(servletRequest);
        requestArgs.setUrl(servletRequest.getRequestURL().toString());
        requestArgs.setPath(servletRequest.getRequestURI());
        requestArgs.setResponse((HttpServletResponse) response);
        requestArgs.setChain(chain);
        return requestArgs;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public boolean isDecodeRequestBody() {
        return decodeRequestBody;
    }

    public void setDecodeRequestBody(boolean decodeRequestBody) {
        this.decodeRequestBody = decodeRequestBody;
    }
}
