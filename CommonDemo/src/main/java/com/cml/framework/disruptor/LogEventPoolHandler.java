package com.cml.framework.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * @Auther: cml
 * @Date: 2018-08-22 16:58
 * @Description:
 */
public class LogEventPoolHandler implements WorkHandler<LogEvent> {
    @Override
    public void onEvent(LogEvent event) throws Exception {
        System.out.println("logEvent:" + event + ",Thread:" + Thread.currentThread().getId());
    }
}
