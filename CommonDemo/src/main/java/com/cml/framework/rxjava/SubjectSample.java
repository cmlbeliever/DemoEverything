package com.cml.framework.rxjava;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Auther: cml
 * @Date: 2018-12-13 14:25
 * @Description:
 */
public class SubjectSample {
    public static void main(String[] args) throws InterruptedException {
        PublishSubject<List<Integer>> subject = PublishSubject.create();
//        subject.reduce(new CopyOnWriteArrayList<>(), (r, t) -> {
//            r.addAll(t);
////            System.out.println("-->" + r);
//            return r;
//        }).subscribe(t -> {
//            System.out.println("dddd" + t);
//        });

        subject.buffer(1, TimeUnit.SECONDS)
                .subscribe(t -> {
                    System.out.println(t);
                });

//        subject.subscribe(t -> {
//            System.out.println("----->" + t);
//        });

        IntStream.range(1, 50).forEach(t -> {
            new Thread() {
                @Override
                public void run() {
                    subject.onNext(Arrays.asList(t));
                }
            }.start();
        });
        Thread.sleep(2000);
        subject.onCompleted();
    }
}
