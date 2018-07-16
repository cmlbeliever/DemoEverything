package com.cml.framework.guava;

import java.util.concurrent.TimeoutException;

public class RetryableHelper {
    public static void main(String[] args) {
        try {
            RetryableHelper.retry(5, 100, () -> {
                throw new TimeoutException("xx");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class RetryFailException extends RuntimeException {
        public RetryFailException() {
        }

        public RetryFailException(String message) {
            super(message);
        }
    }

    @FunctionalInterface
    public static interface RetrySupplier<T> {
        T get() throws Exception;
    }

    /**
     * 重试机制处理
     *
     * @param maxTimes    最多重试次数
     * @param periodDelay 每次重试间隔 ，-1 ： 立即重试
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> T retry(int maxTimes, long periodDelay, RetrySupplier<T> supplier) throws Exception {

        for (int i = 0; i < maxTimes; i++) {
            try {
                return supplier.get();
            } catch (Exception e) {
                //达到最多重试次数
                if (i == (maxTimes - 1)) {
                    throw e;
                }
                try {
                    if (periodDelay != -1) {
                        Thread.sleep(periodDelay);
                    }
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        throw new RetryFailException();
    }
}
