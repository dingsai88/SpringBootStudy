package com.ding.study.nowcoder.a2Array_GetLeastNumbersSolution;

import com.ding.study.util.JsonUtils;

import java.util.ArrayList;

/**
 * 题目描述
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 *
 * @author daniel 2021-1-6 0006.
 */
public class GetLeastNumbersSolutionTest {

    public static void main(String[] args) {
        int[] input = new int[]{9, 8, 1, 2, 3, 4, 5, 6, 7};
        System.out.println(JsonUtils.convertObjToJsonString(getLeastNumbersSolution(input, 4)));
    }

    /**
     * 题目描述
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
     *
     * @param input
     * @param k
     * @return
     */
    public static ArrayList<Integer> getLeastNumbersSolution(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (input == null || k < 1 || k > input.length) {
            return result;
        }

        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                 if (input[i] > input[j]) {
                    int t = input[j];
                    input[j] = input[i];
                    input[i] = t;
                }
            }
        }

        for (int i = 0; i < k; i++) {
            result.add(input[i]);
        }
        return result;
    }
}
