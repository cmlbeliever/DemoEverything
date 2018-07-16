package com.cml.request.distribute.autoconfig.dispatcher;

public class StringRequestDispatcher implements RequestDispatcher<String> {
    @Override
    public String dispatcher(String url, String requestBody) {
        return url + ":" + requestBody;
    }
}
