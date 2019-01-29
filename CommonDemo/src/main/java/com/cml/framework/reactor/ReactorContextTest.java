package com.cml.framework.reactor;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ReactorContextTest {
    public static void main(String[] args) {
        String key = "message";
        Mono<String> r = Mono.just("Hello")
                .flatMap(s -> Mono.subscriberContext().map(ctx -> s + " " + ctx.get(key)))
                .subscriberContext(ctx -> ctx.put(key, "World"));

        Mono.just("test")
                .subscriberContext(t -> t.put("xxx", "上游设置的值下游无法获取到"))
                .flatMap(t -> Mono.subscriberContext().map(ctx -> t + ":" + ctx.getOrEmpty("xxx")))
                .doOnNext(System.out::println)
                .subscribe();

        Mono.just("hello")
                .flatMap(s -> Mono.subscriberContext())
                .map(t -> t.get("request"))
                .subscriberContext(ctx -> ctx.put("request", "下游设置数据给上游"))
                .doOnNext(t -> {
                    System.out.println("t:" + t);
                }).subscribe();

        StepVerifier.create(r)
                .expectNext("Hello World")
                .verifyComplete();
    }
}
