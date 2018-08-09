package com.cml.framework.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.util.Assert;

/**
 * @Auther: cml
 * @Date: 2018-07-31 16:17
 * @Description:
 */
public class RateTest {
    public static void main(String[] args) throws InterruptedException {
        double rate = 10;

        RateLimiter rateLimiter = RateLimiter.create(rate);

        Thread.sleep(1000);
        long acquiredCount = 0;
        while (rateLimiter.tryAcquire()) {
            acquiredCount++;
        }
        System.out.println("获取到令牌数：" + acquiredCount);

        Thread.sleep(1000);

        //修改速率
        rate = 500;
        rateLimiter.setRate(rate);

//        Thread.sleep(1000);
        for (int i = 0; i < 100; i++) {
            acquiredCount = 0;
            long time = System.currentTimeMillis() / 1000;
            while (rateLimiter.tryAcquire()) {
                if (time != System.currentTimeMillis() / 1000) {
                    System.out.println("下一秒" + System.currentTimeMillis() / 1000);
                    break;
                }
                acquiredCount++;
            }
            System.out.println("获取到令牌数：" + acquiredCount);
            Thread.sleep(1000);
        }

//        new Thread(() -> {
//            while (true) {
//                int count = 0;
//                while (rateLimiter.tryAcquire()) {
//                    count++;
//                }
//                log("count:" + count);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                try {
//                    Thread.sleep(2000);
//                    rateLimiter.setRate((i + 1) * 10);
//                    log("changeRate:" + (i + 1) * 10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        Thread.sleep(100000);
    }

    static synchronized void log(String log) {
        System.out.println(log);
    }
}
