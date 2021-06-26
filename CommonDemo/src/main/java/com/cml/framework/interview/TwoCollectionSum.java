package com.cml.framework.interview;

import java.util.Arrays;

/**
 * 一个集合判断是否可以拆分成两个一样的集合
 */
public class TwoCollectionSum {
    public static void main(String[] args) {
        int[] nums = new int[]{
                5, 3, 5, 11
        };
        boolean can = canPartition(nums);
        System.out.println("result=" + can);
    }

    private static boolean canPartition(int[] nums) {

        int sum = Arrays.stream(nums).sum();

        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;

        System.out.println(target);

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] | dp[j - num];
            }
        }
        for (int i = 0; i < dp.length; i++) {
            if (dp[i])
                System.out.println(i + ":" + dp[i]);
        }
        return dp[target];
    }
}
