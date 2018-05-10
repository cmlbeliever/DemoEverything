package com.cml.framework.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class RedissionQueneTest {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.99.100:6379");
        RedissonClient redisson = Redisson.create(config);
//        listTest(redisson);
        setTest(redisson);
    }

    private static void setTest(RedissonClient redisson) {
        RSet set = redisson.getSet("setTest");
        System.out.println("初始化数量：" + set.size());

        long start = System.currentTimeMillis();
        int len = 10_0000;
        for (int i = 0; i < len; i++) {
            set.add(UUID.randomUUID().toString());
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        System.out.println("数量：" + set.size());

        System.out.println("=----pop-----------");
        start = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            Object removed = set.removeRandom();
            System.out.println("remove:" + removed);
        }

        System.out.println("移除耗时：" + (System.currentTimeMillis() - start));
        System.out.println("数量：" + set.size());
    }

    private static void listTest(RedissonClient redisson) {
        RList rList = redisson.getList("redisList");

        new Thread(() -> {
            try {
                while (true) {
                    System.out.println("当前队列长度：" + rList.sizeAsync().get());
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("初始化数量：" + rList.size());

        long start = System.currentTimeMillis();
        int len = 10_0000;
        for (int i = 0; i < len; i++) {
            rList.add(UUID.randomUUID().toString());
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        System.out.println("数量：" + rList.size());

        System.out.println("=----pop-----------");
        start = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            rList.fastRemove(0);
        }
        System.out.println("移除耗时：" + (System.currentTimeMillis() - start));
        System.out.println("数量：" + rList.size());
    }
}
