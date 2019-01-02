package com.cml.framework.jdk.future;

import io.netty.util.concurrent.CompleteFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

/**
 * @Auther: cml
 * @Date: 2019-01-02 10:58
 * @Description:
 */
public class CompleteFeatureCombineTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> defaultFeature = CompletableFuture.completedFuture(100);
        List<Integer> values = Arrays.asList(1, 2, 3);

        for (Integer t : values) {
            defaultFeature = defaultFeature.thenCombine(CompletableFuture.supplyAsync(() -> {
                if (t == 3) {
                    throw new RuntimeException("xxx");
                }
                return t;
            }), (a1, a2) -> {
                return a1 + a2;
            });
        }

        try {
            System.out.println(defaultFeature.get());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("22222222");
        }
    }
}
