package com.cml.framework.interview;

/**
 * 全排列
 */
public class AllPermutation {
    public void Perm(int list[], int k, int m) {
        if (k == m) {
            for (int i = 0; i < list.length; i++)
                System.out.print(list[i]);
            System.out.println();
        } else {
            for (int i = k; i <= m; i++) {
                // 从固定的数后第一个依次交换
                Swap(list, k, i);
                Perm(list, k + 1, m);
                // 这组递归完成之后需要交换回来
                Swap(list, k, i);
            }
        }

    }

    public void Swap(int[] list, int i, int j) {
        int t = list[i];
        list[i] = list[j];
        list[j] = t;
    }

    public static void main(String[] args) {
        AllPermutation d = new AllPermutation();
        int[] arr = {1, 2, 3};
        d.Perm(arr, 0, 2);
    }
}
