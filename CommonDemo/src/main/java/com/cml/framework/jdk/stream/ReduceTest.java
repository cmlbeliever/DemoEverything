package com.cml.framework.jdk.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: cml
 * @Date: 2018-10-30 10:52
 * @Description:
 */
public class ReduceTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3", "4");
        String result = list.stream().reduce("xx", (String a, String b) -> a + b);
        System.out.println(result);
    }
}
