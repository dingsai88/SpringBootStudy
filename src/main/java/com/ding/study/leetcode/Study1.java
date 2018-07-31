package com.ding.study.leetcode;

/**
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * <p>
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 *
 * @author daniel 2018-7-31 0031.
 */
public class Study1 {

    public static void main(String[] args) {
        Solution t = new Solution();
        int[] result = t.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }


    public static class Solution {
        public int[] twoSum(int[] nums, int target) {
            int tempA = 0;
            int tempB = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (nums[i] + nums[j] == target) {
                        tempA = i;
                        tempB = j;
                    }
                }
            }
            int[] result = new int[]{tempA, tempB};
            return result;
        }
    }
}
