package com.cml.framework.reactor;

import reactor.core.publisher.Mono;

/**
 * @Auther: cml
 * @Date: 2019-01-28 10:09
 * @Description:
 */
public class ReactorTest {
    public static void main(String[] args) {
        System.out.println("=====start=====");
        Mono.just(getValue());
        System.out.println("test just");
        Mono.fromSupplier(ReactorTest::getValue);
        System.out.println("test fromSupplier");
    }

    private static String getValue() {
        System.out.println("getvalue");
        return "getValue";
    }
}
