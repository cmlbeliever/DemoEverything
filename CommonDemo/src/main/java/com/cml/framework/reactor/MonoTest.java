package com.cml.framework.reactor;

import reactor.core.publisher.Mono;

/**
 * @Auther: cml
 * @Date: 2019-01-28 10:11
 * @Description:
 */
public class MonoTest {
    public static void main(String[] args) {
        Mono.just(3).doOnNext(t -> System.out.println(t))
                .doOnSuccess(t -> System.out.println("onSuccess"))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .doFinally(t -> System.out.println("doFinally"))
                .subscribe();

    }
}
