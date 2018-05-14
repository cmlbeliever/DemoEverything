package com.cml.framework.jdk.lock;

public class SynchronizedTest {

    public static void main(String[] args) {
        String key = "test";
        for (int i = 0; i < 2; i++) {
            lock(new String("test"));
        }
    }

    static void lock(final String key) {
        new Thread(() -> {
            synchronized (key.intern()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("锁住了：" + Thread.currentThread().getId());
            }
        }).start();
    }
}
