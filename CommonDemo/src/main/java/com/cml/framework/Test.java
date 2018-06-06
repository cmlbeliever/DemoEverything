package com.cml.framework;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        System.out.println("---"+ Lists.newArrayList("222"));
        System.out.println(Arrays.asList().stream().collect(Collectors.toList()));
        System.out.println(new BigDecimal(555.223).setScale(6,RoundingMode.HALF_UP).toPlainString());
    }
}
