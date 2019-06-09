package com.cml.framework.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * zookeeper 客户端 curator测试工具类
 *
 * @author cml
 */
public class CuratorClientTest {
    private static final String CONN_URL = "192.168.99.100:2181";

    private static final String LOCK_PATH = "/lockService/testKeyZK";

    private static CuratorFramework client;

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient(CONN_URL, retryPolicy);
        long startTime = System.currentTimeMillis();
        client.start();

        System.out.println("zookeeper 连接成功！！！" + (System.currentTimeMillis() - startTime));

        // 节点查询与创建
        String testPath = "/category/test1";

        PathChildrenCache nodeCache = new PathChildrenCache(client, testPath, true);
        nodeCache.start();
        nodeCache.getListenable().addListener(new PathChildrenCacheListener() {

            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case CHILD_ADDED:
                        System.out.println("add:" + event.getData());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("update:" + event.getData());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("remove:" + event.getData());
                        break;
                    default:
                        break;
                }
            }
        });

        Stat testPathStat = client.checkExists().forPath(testPath);
        if (testPathStat == null) {
            System.out.println("节点不存在，创建节点：" + testPath);
            client.create().withMode(CreateMode.PERSISTENT).forPath(testPath, "dataValue".getBytes());
        } else {
            client.setData().forPath(testPath, ("change" + System.currentTimeMillis()).getBytes());
            System.out.println("获取节点的内容：" + new String(client.getData().forPath(testPath)));
        }

        // 测试多线程锁处理
        testLock();

        List<String> list = client.getChildren().forPath("/cml");
        System.out.println(list);

        Thread.sleep(1000000);

    }

    /**
     * 模拟多线程分布式锁功能
     */
    private static void testLock() {
        for (int i = 0; i < 10; i++) {
            final int index = i;
            new Thread(() -> {
                try {
                    distributeLockTest(index);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void distributeLockTest(int i) throws Exception {
        InterProcessMutex lock = new InterProcessMutex(client, LOCK_PATH);
        if (lock.acquire(0, TimeUnit.MICROSECONDS)) {
            try {
                Thread.sleep(1000);
                System.out.println("获取到分布式锁了！！！！" + i);
            } finally {
                lock.release();
            }
        } else {
            // 没有获取到锁 休眠1s再次尝试
            System.out.println("未获取到");
//            Thread.sleep(1000);
//            distributeLockTest(i);
        }
    }
}
