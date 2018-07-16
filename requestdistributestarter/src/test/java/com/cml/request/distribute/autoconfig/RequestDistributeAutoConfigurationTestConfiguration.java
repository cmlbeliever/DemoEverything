package com.cml.request.distribute.autoconfig;

import com.cml.request.distribute.autoconfig.anon.EnableAutoDistributeRequest;
import com.cml.request.distribute.autoconfig.dispatcher.RequestDispatcher;
import com.cml.request.distribute.autoconfig.dispatcher.StringRequestDispatcher;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableAutoDistributeRequest
@SpringBootApplication
public class RequestDistributeAutoConfigurationTestConfiguration {

    @Bean
    public RequestDispatcher<String> stringRequestDispatcher() {
        return new StringRequestDispatcher();
    }

    @Bean
    public RequestDistributeCallback<RequestArgs, Void> callback(final RequestDispatcher<String> stringRequestDispatcher) {
        return new RequestDistributeCallback<RequestArgs, Void>() {
            @Override
            public Void distributeOnError(Exception e, boolean aquiredToken, RequestArgs requestArgs) {
//                System.out.println("distributeOnError");
                e.printStackTrace();
                if (aquiredToken) {
                    try {
                        distributeWithoutToken(requestArgs);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }

                return null;
            }

            @Override
            public Void distributeWithoutToken(RequestArgs requestArgs) throws Exception {
                String response = stringRequestDispatcher.dispatcher(requestArgs.getUrl(), requestArgs.getRequestBody());
                requestArgs.getResponse().getWriter().write(response.toCharArray());
                requestArgs.getResponse().getWriter().flush();
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
