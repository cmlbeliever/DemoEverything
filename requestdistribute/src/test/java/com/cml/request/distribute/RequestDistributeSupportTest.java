package com.cml.request.distribute;

import com.cml.request.distribute.config.RequestDistributeConfig;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class RequestDistributeSupportTest {
    @Test
    public void testDistribute() {
        RequestDistributeSupport<RequestArgs> support = new RequestDistributeSupport<RequestArgs>() {
            @Override
            protected String distributeOnError(Exception e, boolean aquiredToken, RequestArgs requestArgs) {
//                System.out.println("distributeOnError");
                e.printStackTrace();
                if (aquiredToken) {
                    return distributeWithoutToken(requestArgs);
                }
                return e.getMessage();
            }

            @Override
            protected String distributeOnGetToken(RequestArgs requestArgs) {
//                System.out.println("distributeOnGetToken");
                if (true)
                    throw  new RuntimeException("exception");
                return "distributeOnGetToken";
            }

            @Override
            protected String distributeWithoutToken(RequestArgs requestArgs) {
//                System.out.println("distributeWithoutToken");
                return requestArgs.getUrl();
            }
        };
        support.setGroupManager(new DistributeGroupManager<RequestArgs>() {
            @Override
            public String getGroup(RequestArgs requestArgs) {
                return requestArgs.getUrl();
            }
        });

        DefaultDistributeRateLimiter rateLimiter = new DefaultDistributeRateLimiter();
        RequestDistributeConfig config = new RequestDistributeConfig();
        config.setEnable(true);
        config.setRate(0.000001);
        RequestDistributeConfig.DistributePolicy distributePolicy = new RequestDistributeConfig.DistributePolicy();
        distributePolicy.setGroup("kkkk");
        distributePolicy.setRate(10);
        config.getPolicy().add(distributePolicy);
        rateLimiter.setRequestDistributeConfig(config);
        rateLimiter.init();

        support.setRateLimiter(rateLimiter);

        final AtomicInteger tokenCount = new AtomicInteger(0);
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                String result = support.distribute(new RequestArgs("kkkk", "", null));
                if ("distributeOnGetToken".equals(result)) {
                    tokenCount.incrementAndGet();
                }
                if (tokenCount.get() > 0)
                    System.out.println("result:" + result + ":" + tokenCount.get());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

}
