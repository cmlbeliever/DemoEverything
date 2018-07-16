package com.cml.request.distribute.autoconfig;

public interface RequestDistributeCallback<T, R> {
    /**
     * distributeWithoutToken或distributeOnGetToken执行出错时回调
     *
     * @param e
     * @param aquiredToken true：已经获取到令牌
     * @param requestArgs
     * @return
     */
    R distributeOnError(Exception e, boolean aquiredToken, T requestArgs);

    R distributeWithoutToken(T requestArgs) throws Exception;

    R distributeOnGetToken(T requestArgs) throws Exception;
}
