package com.cml.request.distribute.autoconfig.dispatcher;

public interface RequestDispatcher<T> {
    T dispatcher(String url, String requestBody);
}
