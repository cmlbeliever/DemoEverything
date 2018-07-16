package com.cml.request.distribute.autoconfig;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RequestDistributeAutoConfiguration.class)
public class RequestDistributeAutoConfigurationTestConfiguration {
    @Bean
    public RequestDistributeCallback<RequestArgs, Void> callback() {
        return new RequestDistributeCallback<RequestArgs, Void>() {
            @Override
            public Void distributeOnError(Exception e, boolean aquiredToken, RequestArgs requestArgs) {
                System.out.println("distributeOnError");
                e.printStackTrace();
                return null;
            }

            @Override
            public Void distributeWithoutToken(RequestArgs requestArgs) throws Exception {
                System.out.println("distributeWithoutToken");
                return null;
            }

            @Override
            public Void distributeOnGetToken(RequestArgs requestArgs) throws Exception {
                requestArgs.doFilter();
                return null;
            }
        };
    }
}
