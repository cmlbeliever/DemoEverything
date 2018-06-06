package com.cml.framework.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class HttpClientUtil {

    private int connTimeout = 10_000;
    private CloseableHttpClient httpClient;

    @PostConstruct
    public void init() {
        //定义超时请求
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(connTimeout) //连接请求超时
                .setConnectTimeout(connTimeout) //连接超时
                .setSocketTimeout(connTimeout) //socket 内等待数据超时
                .build();

        //全局设置
        httpClient = HttpClients.custom()
                .setMaxConnPerRoute(1000)
                .setMaxConnTotal(2000)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    public String post(HttpPost httpPost, String charset) throws IOException {
        return request(httpPost, charset);
    }

    public String post(String url, String body, String charset) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        HttpEntity httpEntity = new StringEntity(body, Charset.forName(charset));
        httpPost.setEntity(httpEntity);
        return request(httpPost, charset);
    }

    public String get(HttpGet httpGet, String charset) throws IOException {
        return request(httpGet, charset);
    }

    private String request(HttpUriRequest request, String charset) throws IOException {
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return getResponseStr(response, charset);
        }
    }

    private String getResponseStr(CloseableHttpResponse response, String charset) throws IOException {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instreams = entity.getContent();
            return IOUtils.toString(instreams, Charset.forName(charset));
        }
        return null;
    }
}
