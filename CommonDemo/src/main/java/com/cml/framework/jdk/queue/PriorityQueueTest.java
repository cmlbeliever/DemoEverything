package com.cml.framework.jdk.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Auther: cml
 * @Date: 2018-08-15 17:00
 * @Description:
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> priorityQueue = new PriorityBlockingQueue<>(10000, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                priorityQueue.add(i);
            }
        }).start();
        while (true) {
            try {
                System.out.println(priorityQueue.take());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
