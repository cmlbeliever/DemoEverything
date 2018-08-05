package com.cml.framework.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Disruptor 定义了 com.lmax.disruptor.WaitStrategy 接口用于抽象 Consumer 如何等待新事件，这是策略模式的应用。
 * Disruptor 提供了多个 WaitStrategy 的实现，每种策略都具有不同性能和优缺点，根据实际运行环境的 CPU 的硬件特点选择恰当的策略，并配合特定的 JVM 的配置参数，能够实现不同的性能提升。
 * 例如，BlockingWaitStrategy、SleepingWaitStrategy、YieldingWaitStrategy 等，其中，
 * BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现；
 * SleepingWaitStrategy 的性能表现跟 BlockingWaitStrategy 差不多，对 CPU 的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景；
 * YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于 CPU 逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性。
 */
public class Entrance {
    public static void main(String[] args) throws InterruptedException {
//        WaitStrategy BLOCKING_WAIT = new BlockingWaitStrategy();
//        WaitStrategy SLEEPING_WAIT = new SleepingWaitStrategy();
//        WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();

//        RingBuffer<LogEvent> ringBuffer = RingBuffer.create(ProducerType.MULTI, new LogEventFactory(), 1024, new YieldingWaitStrategy());
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Disruptor<LogEvent> disruptor = new Disruptor<LogEvent>(new LogEventFactory(), 1024, executorService);
        disruptor.handleEventsWith(new LogEventHandler());
        disruptor.start();

        RingBuffer ringBuffer = disruptor.getRingBuffer();
        final LogEventProducer logEventProducer = new LogEventProducer(ringBuffer);


        for (int i = 0; i < 100; i++) {
            String log = "log Event:" + i;
            new Thread(() -> {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logEventProducer.send(log);

            }).start();
        }
        Thread.sleep(5000);
        disruptor.shutdown();
        executorService.shutdown();

    }
}
