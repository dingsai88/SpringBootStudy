package com.ding.study.nowcoder.a13maxLength;

import com.ding.study.util.JsonUtils;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int maxLength(int[] arr) {
        int max = 0;
        if (arr == null) {
            return max;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (set.contains(arr[j])) {
                    break;
                }
                set.add(arr[j]);
            }

            max = max > set.size() ? max : set.size();
            set.clear();
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = new int[]{3, 2, 4};
        System.out.println(JsonUtils.convertObjToJsonString(solution.maxLength(a)));
    }

}
