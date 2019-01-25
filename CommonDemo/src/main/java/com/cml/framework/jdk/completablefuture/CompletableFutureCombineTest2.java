package com.cml.framework.jdk.completablefuture;

import rx.Observable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * @Auther: cml
 * @Date: 2019-01-25 09:13
 * @Description:
 */
public class CompletableFutureCombineTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> defaultConsumerFuture = CompletableFuture.completedFuture(0);
        for (int i = 1; i < 4; i++) {
            final int index = i;
            CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> index).thenApply(t -> {
                System.out.println("apply::" + t + ":" + Thread.currentThread().getId());
                return t * 10;
            });
            defaultConsumerFuture = defaultConsumerFuture.thenCombine(integerCompletableFuture, Integer::max);
        }

        System.out.println("===>" + defaultConsumerFuture.get());
    }
}
