package com.ding.study.nowcoder.a19Array_MaxsumofSubarray;

/**
 * NC19 子数组的最大累加和问题
 * <p>
 * 描述
 * 给定一个数组arr，返回子数组的最大累加和
 * 例如，arr = [1, -2, 3, 5, -2, 6, -1]，所有子数组中，[3, 5, -2, 6]可以累加出最大的和12，所以返回12.
 * 题目保证没有全为负数的数据
 * [要求]
 * 时间复杂度为O(n)O(n)，空间复杂度为O(1)O(1)
 */
public class SolutionMaxsumofSubarray {
    /**
     * @param arr
     * @return
     */
    public int maxsumofSubarray(int[] arr) {
        int max = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            max = sum > max ? sum : max;
            sum = sum > 0 ? sum : 0;
        }
        return max;
    }


}
