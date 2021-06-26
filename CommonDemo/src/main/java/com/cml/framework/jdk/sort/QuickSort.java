package com.cml.framework.jdk.sort;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @Auther: cml
 * @Date: 2018-12-04 15:16
 * @Description:
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] source = new int[]{6, 1, 2, 7, 1, 3, 565, 9, 30, 4, 5, 10, 8};
        quickSort(source, 0, source.length - 1);
        IntStream.of(source).forEach(t -> {
            System.out.print(t + ",");
        });
    }

    private static void quickSort(int[] source, int start, int end) {

        if (start >= end) {
            return;
        }

        int temp = source[start];

        int i = start;
        int j = end;

        while (i < j) {
            while (source[j] >= temp && j > i) {
                j--;
            }
            while (source[i] <= temp && j > i) {
                i++;
            }
            if (i < j) {
                int t = source[i];
                source[i] = source[j];
                source[j] = t;
            }
        }

        source[start] = source[i];
        source[i] = temp;

        quickSort(source, start, i - 1);
        quickSort(source, i + 1, end);
    }
}
