package com.cml.framework.jdk.completablefuture;

import rx.Observable;

import java.util.concurrent.CompletableFuture;

/**
 * @Auther: cml
 * @Date: 2019-01-25 09:13
 * @Description:
 */
public class CompletableFutureCombineTest {
    public static void main(String[] args) {
        // 合并两个异步的处理数据
        String combineResult = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("----开始执行1：" + Thread.currentThread().getId());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "value from 1 " + Thread.currentThread().getId();
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("----开始执行2：" + Thread.currentThread().getId());
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "value from 2 " + Thread.currentThread().getId();
        }), (v1, v2) -> {
            return v1 + "," + v2 + ",mergeThreadId:" + Thread.currentThread().getId();
        }).join();
        System.out.println("==>2:合并两个请求值：" + combineResult);

        Observable.zip(Observable.just(1), Observable.just(2), Observable.just(3), (a, b, c) -> {
            return a + b + c;
        }).doOnNext(t -> {
            System.out.println("ttttt:" + t);
        }).subscribe();

//        //
//        CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "hello " + Thread.currentThread().getId();
//        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "world " + Thread.currentThread().getId();
//        }), (s1, s2) -> System.out.println(s1 + " " + s2 + "," + Thread.currentThread().getId()));

    }
}
