package com.cml.framework.jdk.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Auther: cml
 * @Date: 2018-07-27 13:48
 * @Description:
 */
public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("=====time===>" + Thread.currentThread().getId());
            }
        }, 0L, 1000L);

        System.out.println("end==>" + Thread.currentThread().getId());
    }
}
