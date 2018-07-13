package com.cml.request.distribute;

public interface RequestDistribute<T, R> {

    /**
     * 进行请求分发
     *
     * @param t
     * @return
     */
    R distribute(T t);
}
