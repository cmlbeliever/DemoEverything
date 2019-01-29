package com.cml.framework.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ReactorParallel {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 10)
                .parallel(8)
                .runOn(Schedulers.parallel())
                .subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
        Thread.sleep(100);
        System.out.println("--------------------------------");
        Flux.range(1, 10)
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
    }
}
