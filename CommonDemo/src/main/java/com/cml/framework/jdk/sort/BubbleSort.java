package com.cml.framework.jdk.sort;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @Auther: cml
 * @Date: 2018-12-03 16:38
 * @Description:
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] source = new int[]{1, 2, 6, 4, 88, 34, 86, 32, 12, 0, -1, 73};
        IntStream.of(sort(source)).forEach(t -> {
            System.out.print(t + ",");
        });
        source = new int[]{1, 2, 6, 4, 88, 34, 86, 32, 12, 0, -1, 731};
        System.out.println("\n================================");
        IntStream.of(sort2(source)).forEach(t -> {
            System.out.print(t + ",");
        });
    }

    public static int[] sort(int[] source) {
        int len = source.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (source[i] > source[j]) {
                    int temp = source[i];
                    source[i] = source[j];
                    source[j] = temp;
                }
            }
        }
        return source;
    }

    public static int[] sort2(int[] source) {
        int len = source.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (source[i] > source[j]) {
                    source[i] = source[i] + source[j];
                    source[j] = source[i] - source[j];
                    source[i] = source[i] - source[j];
                }
            }
        }
        return source;
    }
}
