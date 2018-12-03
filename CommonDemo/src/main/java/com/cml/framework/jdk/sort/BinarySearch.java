package com.cml.framework.jdk.sort;

import java.util.Collections;

/**
 * @Auther: cml
 * @Date: 2018-12-03 16:52
 * @Description:
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] source = new int[]{1, 2, 5, 6, 8, 9, 11, 22, 33, 55, 77};

        System.out.println(binarySearch(source, 7));
        System.out.println(binarySearch(source, 8));
        System.out.println(binarySearch(source, 55));
        System.out.println(binarySearch(source, 77));
        System.out.println(binarySearch(source, 771));

    }

    private static int binarySearch(int[] source, int target) {
        int start = 0;
        int end = source.length;
        while (start < end) {
            int middleIndex = (start + end) >>> 1;
            int middle = source[middleIndex];
            if (middle == target) {
                return middleIndex;
            }
            if (target > middle) {
                start = middleIndex + 1;
            } else {
                end = middleIndex;
            }
        }
        return -1;
    }
}
