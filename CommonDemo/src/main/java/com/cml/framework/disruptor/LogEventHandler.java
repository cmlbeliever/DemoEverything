package com.cml.framework.disruptor;


import com.lmax.disruptor.EventHandler;

public class LogEventHandler implements EventHandler<LogEvent> {
    @Override
    public void onEvent(LogEvent logEvent, long l, boolean b) throws Exception {
        System.out.println("logEvent:" + logEvent + ",:l:" + l + ",boolean:" + b);
    }
}
