package com.ding.study.nowcoder.a6Array_upperbound;

/**
 * 二分查找
 * https://www.nowcoder.com/practice/7bc4a1c7c371425d9faa9d1b511fe193?tpId=190&&tqId=35227&rp=1&ru=/ta/job-code-high-rd&qru=/ta/job-code-high-rd/question-ranking
 *
 *题目描述
 * 请实现有重复数字的有序数组的二分查找。
 * 输出在数组中第一个大于等于查找值的位置，如果数组中不存在这样的数，则输出数组长度加一。
 *
 */
public class Solution {


    public static void main(String[] args) {
        int[] i = {1, 2, 4, 4, 5};
        System.out.println(new Solution().upper_bound_(5, 4, i));

    }

    public int upper_bound_ (int n, int v, int[] a) {
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (a[m] >= v) {
                if (m == 0 || a[m - 1] < v) {
                    return m + 1;
                } else {
                    r = m;
                }
            } else {
                l = m + 1;
            }
        }
        return n + 1;
    }
}
