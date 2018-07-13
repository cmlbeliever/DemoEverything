package com.cml.request.distribute;

public abstract class AbstractRequestDistribute<T,R> implements RequestDistribute<T,R> {

    public R distribute(T t) {
        boolean aquiredToken = tryAquireToken(t);
        try {
            if (aquiredToken) {
                return distributeOnGetToken(t);
            }
            return distributeWithoutToken(t);
        } catch (Exception e) {
            return distributeOnError(e, aquiredToken, t);
        }
    }

    /**
     * 操作失败时回调
     *
     * @param e
     * @param aquiredToken true：已经获得分发的令牌
     * @param t
     * @return
     */
    protected abstract R distributeOnError(Exception e, boolean aquiredToken, T t);

    protected abstract R distributeWithoutToken(T t) throws Exception;

    protected abstract R distributeOnGetToken(T t) throws Exception;

    /**
     * 获取分发令牌
     *
     * @param t
     * @return
     */
    protected abstract boolean tryAquireToken(T t);

}
