package com.cml.request.distribute.autoconfig;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public interface RequestArgConverter<T> {
    T convert(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException;
}
