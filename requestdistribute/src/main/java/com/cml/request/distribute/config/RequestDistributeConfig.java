package com.cml.request.distribute.config;

import java.util.ArrayList;
import java.util.List;

public class RequestDistributeConfig {
    private boolean enable = true;
    private List<DistributePolicy> policy = new ArrayList<DistributePolicy>();
    private double rate = 1;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<DistributePolicy> getPolicy() {
        return policy;
    }

    public void setPolicy(List<DistributePolicy> policy) {
        this.policy = policy;
    }

    public static class DistributePolicy {
        private String group;
        private double rate = 1;
        private boolean enable = true;

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }
    }
}
