package com.cml.framework.rxjava;

import rx.subjects.PublishSubject;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Auther: cml
 * @Date: 2018-12-13 14:25
 * @Description:
 */
public class SubjectSample2 {
    public static void main(String[] args) throws InterruptedException {
        PublishSubject<Integer> subject = PublishSubject.create();

        subject.buffer(1, TimeUnit.SECONDS)
                .subscribe(t -> {
                    System.out.println(Thread.currentThread().getId() + "," + t);
                });

//        subject.subscribe(t -> {
//            System.out.println("----->" + t);
//        });

        IntStream.range(1, 50).forEach(t -> {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 5000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subject.onNext(t);
                }
            }.start();
        });
        Thread.sleep(10000);
        subject.onCompleted();
        System.out.println("completed");
        Thread.sleep(5000);
        System.out.println("end");
    }
}
