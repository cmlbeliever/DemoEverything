package com.cml.request.distribute.autoconfig;

import com.cml.request.distribute.RequestDistributeSupport;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

public class FilterRequestDistribute extends RequestDistributeSupport<RequestArgs, Void> implements Filter {

    private RequestDistributeCallback<RequestArgs, Void> callback;

    @Override
    protected Void distributeWithoutToken(RequestArgs requestArgs) throws Exception {
        if (null != callback) {
            callback.distributeWithoutToken(requestArgs);
        }
        return null;
    }

    @Override
    protected Void distributeOnError(Exception e, boolean aquiredToken, RequestArgs requestArgs) {
        if (null != callback) {
            callback.distributeOnError(e, aquiredToken, requestArgs);
        }
        return null;
    }

    @Override
    protected Void distributeOnGetToken(RequestArgs requestArgs) throws Exception {
        //正常请求
        if (null != callback) {
            callback.distributeOnGetToken(requestArgs);
        }
        return null;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestArgs requestArgs = new RequestArgs();
        requestArgs.setChain(chain);
        requestArgs.setRequest((HttpServletRequest) request);
        requestArgs.setResponse((HttpServletResponse) response);
        requestArgs.setPath(((HttpServletRequest) request).getRequestURI());
        requestArgs.setRequestBody(getRequestBody((HttpServletRequest) request));
        this.distribute(requestArgs);
    }

    protected String getRequestBody(HttpServletRequest request) {
        return null;
    }

    @Override
    public void destroy() {

    }

    public void setCallback(RequestDistributeCallback<RequestArgs, Void> callback) {
        this.callback = callback;
    }
}
