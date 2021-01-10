package com.ding.study.nowcoder.a10DiGui_JumpFloor;

import com.ding.study.util.JsonUtils;

public class Solution {

    public static void main(String[] args) {
        System.out.println(JsonUtils.convertObjToJsonString(new Solution().JumpFloor(5)));

    }

    /**
     * 跳房子
     * @param target
     * @return
     */
    public  int JumpFloor(int target) {
        if (target <= 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int[] a = new int[target+1];
        a[1]=1;
        a[2]=2;
        for (int i = 3; i <= target; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }
        return a[target];
    }


}
