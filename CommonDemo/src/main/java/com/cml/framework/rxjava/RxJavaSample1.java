package com.cml.framework.rxjava;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: cml
 * @Date: 2018-12-13 13:51
 * @Description:
 */
public class RxJavaSample1 {
    public static void main(String[] args) throws InterruptedException {
        Observable.just(1, 3, 4, 5, 6, 7).debounce(1000, TimeUnit.MILLISECONDS)
                .doOnNext(t -> {
                    System.out.println("threadId:" + Thread.currentThread().getId() + "-----------" + t);
                })
                .subscribe();

        Observable.just(1, 2, 8)
                .reduce((r, t) -> r + t)
                .delay(1000, TimeUnit.MILLISECONDS)
                .doOnNext(t -> {
                    System.out.println("threadId:" + Thread.currentThread().getId() + "," + t);
                })
                .subscribeOn(Schedulers.io())
                .subscribe();

        Thread.sleep(2000);
    }
}
