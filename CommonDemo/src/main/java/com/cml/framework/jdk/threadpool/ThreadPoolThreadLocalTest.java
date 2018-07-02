package com.cml.framework.jdk.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ThreadPoolThreadLocalTest implements Runnable {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        IntStream.range(1, 10).forEach(t -> {
            executorService.submit(new ThreadPoolThreadLocalTest(t));
        });
    }

    private int index;
    static ThreadLocal threadLocal = new ThreadLocal();

    public ThreadPoolThreadLocalTest(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println("index:" + index + ":threadLocal:" + threadLocal.get());
        threadLocal.set(index);
    }
}
