package com.cml.framework.disruptor;

public class LogEvent {
    private String log;

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public String toString() {
        return "LogEvent{" +
                "log='" + log + '\'' +
                '}';
    }
}
