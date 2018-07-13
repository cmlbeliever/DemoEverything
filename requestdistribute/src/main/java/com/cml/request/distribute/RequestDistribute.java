package com.cml.request.distribute;

public interface RequestDistribute<T> {

    /**
     * 进行请求分发
     *
     * @param t
     * @return
     */
    String distribute(T t);
}
