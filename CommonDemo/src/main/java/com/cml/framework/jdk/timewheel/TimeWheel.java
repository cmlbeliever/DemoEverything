package com.cml.framework.jdk.timewheel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: cml
 * @Date: 2018-10-31 17:54
 * @Description:
 */
public class TimeWheel<T> {
    private int slotSize;
    private List<Node<T>> data;
    private long startTime;
    private ReentrantLock lock = new ReentrantLock();

    public TimeWheel(int slotSize) {
        this.slotSize = slotSize;
        data = new ArrayList<>(slotSize);
        startTime = System.currentTimeMillis();
    }

    public int addTask(T t, long expectTime) {

        long timeInterval = System.currentTimeMillis() - expectTime;
        if (timeInterval < 0) {
            throw new IllegalArgumentException("执行时间需要大于当前时间");
        }
        lock.lock();
        try {
            int index = (int) (timeInterval % slotSize);

            Node<T> node = new Node<>();
            node.data = t;
            node.index = index;
            data.add(node);

            return index;
        } finally {
            lock.unlock();
        }
    }

    private static class Node<T> {
        private T data;
        private int index;
        private boolean executed;
    }
}
