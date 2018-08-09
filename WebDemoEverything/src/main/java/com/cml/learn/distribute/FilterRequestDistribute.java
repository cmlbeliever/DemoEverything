package com.cml.learn.distribute;

import com.cml.request.distribute.RequestDistributeSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterRequestDistribute extends RequestDistributeSupport<RequestArgs,String> implements Filter {

    @Override
    protected String distributeWithoutToken(RequestArgs requestArgs) throws Exception {
        //TODO 转发出去
        return null;
    }

    @Override
    protected String distributeOnError(Exception e, boolean aquiredToken, RequestArgs requestArgs) {
        //内部错误
        if (aquiredToken) {
            //异常处理
        } else {
            try {
                return distributeWithoutToken(requestArgs);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected String distributeOnGetToken(RequestArgs requestArgs) throws Exception {
        //正常请求
        requestArgs.doFilter();
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
        this.distribute(requestArgs);
    }

    @Override
    public void destroy() {

    }
}
