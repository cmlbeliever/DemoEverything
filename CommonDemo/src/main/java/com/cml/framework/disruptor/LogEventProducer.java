package com.cml.framework.disruptor;

import com.lmax.disruptor.RingBuffer;

public class LogEventProducer {
    private final RingBuffer<LogEvent> ringBuffer;

    public LogEventProducer(RingBuffer<LogEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void send(String log) {
        long sequence = ringBuffer.next();
//        System.out.println("producer:" + sequence);
        try {
            LogEvent event = ringBuffer.get(sequence);
            event.setLog(log);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
