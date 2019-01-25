package com.cml.framework.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Auther: cml
 * @Date: 2019-01-25 15:45
 * @Description:
 */
public class HystrixTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        Callable callable = () -> "执行xx业务逻辑：" + Thread.currentThread().getId();
        Callable fallbackCallable = () -> {
            throw new RuntimeException("xxx");
        };

        System.out.println(new TestCommand("group1", callable).execute());
        System.out.println(new TestCommand("group1", callable).execute());
        System.out.println(new TestCommand("group1", callable).execute());
        System.out.println(new TestCommand("group1", callable).execute());
        System.out.println(new TestCommand("group1", callable).execute());
        System.out.println(new TestCommand("group1", callable).execute());
        System.out.println(new TestCommand("group1", fallbackCallable).execute());

        System.out.println("Thread:" + Thread.currentThread().getId());

        //异步执行
        Future<String> result = new TestCommand("group1", callable).queue();
        System.out.println("result:" + result.get());
    }

    static class TestCommand extends HystrixCommand<String> {

        private Callable<String> callable;

        protected TestCommand(String group, Callable callable) {
            super(HystrixCommandGroupKey.Factory.asKey(group));
            this.callable = callable;
        }

        @Override
        protected String run() throws Exception {
            return callable.call();
        }

        @Override
        protected String getFallback() {
            return "执行xx业务逻辑getFallback：" + Thread.currentThread().getId();
        }
    }
}
