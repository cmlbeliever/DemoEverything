package com.cml.request.distribute;

public class RequestDistributeSupport<T> extends AbstractTokenRequestDistribute<T> {

    @Override
    protected String distributeOnError(Exception e, boolean aquiredToken, T requestArgs) {
        return null;
    }

    @Override
    protected String distributeWithoutToken(T requestArgs) {
        return null;
    }

    @Override
    protected String distributeOnGetToken(T requestArgs) {
        return null;
    }
}
