package com.cml.framework.distribute.snowflake;

import java.util.HashSet;
import java.util.Set;

public class SnowflakeIdTest {
    public static void main(String[] args) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(31, 13);
        int count = Integer.MAX_VALUE;
        Set<Long> value = new HashSet<>();
        for (int i = 0; i < count; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
            if (value.contains(id)) {
                System.out.println("遇到重复的了！！！");
                break;
            } else {
                value.add(id);
            }
        }
        System.out.println("执行结束");
    }
}
