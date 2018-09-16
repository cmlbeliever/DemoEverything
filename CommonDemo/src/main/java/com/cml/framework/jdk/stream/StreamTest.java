package com.cml.framework.jdk.stream;

import com.google.common.primitives.Ints;
import io.swagger.models.auth.In;

import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
//        testSort();
//        testParallel();
//        testIntStream();
//        testReduce();
        customSteam();
    }

    private static void customSteam() {
        Stream.generate(() -> {
            return "a" + Math.random();
        }).limit(10).forEach(t -> System.out.println(t));
    }

    private static void testReduce() {
        int sum = IntStream.range(0, 10).reduce(0, Integer::sum);
        System.out.println(sum);
        sum = IntStream.range(0, 9).reduce(0, (a, b) -> {
            System.out.println("a:" + a + ",b:" + b);
            return a + b;
        });
        System.out.println(sum);
        System.out.println(IntStream.range(0, 9).reduce(Integer::sum).getAsInt());
    }

    private static void testIntStream() {
        IntStream.range(0, 100).sorted().forEach(t -> System.out.println(t));

    }

    private static void testParallel() {
        Stream.of(1, 5, 8, 3, 1).parallel().map(t -> {
            System.out.println("testParallel,thread:" + Thread.currentThread().getId());
            return t * 10;
        }).forEach(t -> System.out.println(t));
    }

    private static void testSort() {
        Stream.of(1, 2, 3, 6, 9, 4, 2).sorted(Comparator.comparing(Integer::intValue).reversed()).forEach(t -> System.out.println(t));
    }
}
