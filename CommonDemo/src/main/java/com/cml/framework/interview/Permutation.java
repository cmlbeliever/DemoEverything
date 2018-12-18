package com.cml.framework.interview;

import java.util.Arrays;

public class Permutation {
    static int size = 0;

    public static void main(String[] args) {
        String[] data = {"a", "b", "c"};
        permutation(data, 0);
        System.out.println("共有数据：" + size);
    }

    private static void permutation(String[] str, int start) {
        if (start == str.length) {
            System.out.println(Arrays.asList(str));
            size++;
            return;
        }
        for (int i = start; i < str.length; i++) {
            swap(str, i, start);
            permutation(str, start + 1);
        }
    }

    private static void swap(String[] str, int i, int start) {
        String temp = str[i];
        str[i] = str[start];
        str[start] = temp;
    }
}
