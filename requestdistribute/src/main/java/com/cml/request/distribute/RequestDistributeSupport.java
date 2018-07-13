package com.cml.request.distribute;

public class RequestDistributeSupport<T, R> extends AbstractTokenRequestDistribute<T, R> {

    @Override
    protected R distributeOnError(Exception e, boolean aquiredToken, T requestArgs) {
        return null;
    }

    @Override
    protected R distributeWithoutToken(T requestArgs) throws Exception {
        return null;
    }

    @Override
    protected R distributeOnGetToken(T requestArgs) throws Exception {
        return null;
    }
}
