package com.cml.framework.disruptor;


import com.lmax.disruptor.EventHandler;

public class LogEventHandler implements EventHandler<LogEvent> {
    @Override
    public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("logEvent:" + event + ",sequence:" + sequence + ",endOfBatch:" + endOfBatch + ",Thread:" + Thread.currentThread().getId());
//        Thread.sleep(1000);
    }
}
