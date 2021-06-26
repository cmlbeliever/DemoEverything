package com.cml.framework.interview.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    public int[] mergeSort(int[] num) {
        if (num.length <= 1) return num;
        int mid = num.length / 2;
        int[] left = Arrays.copyOfRange(num, 0, mid);
        int[] right = Arrays.copyOfRange(num, mid, num.length);
        return mergeArrays(mergeSort(left), mergeSort(right));
    }

    private int[] mergeArrays(int[] mergeSort1, int[] mergeSort2) {
        int[] result = new int[mergeSort1.length + mergeSort2.length];
        int i = 0, j = 0, k = 0;
        while (i < mergeSort1.length && j < mergeSort2.length) {
            if (mergeSort1[i] < mergeSort2[j]) {
                result[k++] = mergeSort1[i++];
            } else {
                result[k++] = mergeSort2[j++];
            }
        }
        while (i < mergeSort1.length) {
            result[k++] = mergeSort1[i++];
        }
        while (j < mergeSort2.length) {
            result[k++] = mergeSort2[j++];
        }
        return result;
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] num = {3, 7, 4, 2, 5, 8, 1};
        num = mergeSort.mergeSort(num);
        for (int t : num) {
            System.out.println(t);
        }
    }
}