package com.cml.request.distribute;

public class RequestDistributeSupport extends AbstractTokenRequestDistribute<RequestArgs> {

    @Override
    protected String distributeOnError(Exception e, boolean aquiredToken, RequestArgs requestArgs) {
        return null;
    }

    @Override
    protected String distributeWithoutToken(RequestArgs requestArgs) {
        return null;
    }

    @Override
    protected String distributeOnGetToken(RequestArgs requestArgs) {
        return null;
    }
}
