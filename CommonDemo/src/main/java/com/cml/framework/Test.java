package com.cml.framework;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        System.out.println("---" + Lists.newArrayList("222"));
        System.out.println(Arrays.asList().stream().collect(Collectors.toList()));
        System.out.println(new BigDecimal(555.223).setScale(6, RoundingMode.HALF_UP).toPlainString());


        System.out.println(Optional.ofNullable(null).map(t -> "xxx").orElse("yyyyy"));

        System.out.println(new BigDecimal("100.3000").stripTrailingZeros().toPlainString());
        System.out.println(new BigDecimal("100.3000").toString());
        for (String str : "1,2,,33".split(",")) {
            System.out.println(str.equals(""));
        }

        String directInvoiceMethod = "dFP";
        System.out.println(!directInvoiceMethod.equals("FP") && !directInvoiceMethod.equals("XH") && !directInvoiceMethod.equals("ZF"));

        System.out.println(Arrays.asList("d").contains(null));
    }
}
