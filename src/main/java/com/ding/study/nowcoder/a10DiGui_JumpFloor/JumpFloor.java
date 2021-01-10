package com.ding.study.nowcoder.a10DiGui_JumpFloor;

import com.ding.study.util.JsonUtils;

/**
 * 递归的解法
 */
public class JumpFloor {


    public static void main(String[] args) {
        System.out.println(JsonUtils.convertObjToJsonString(new JumpFloor().jumpFloor(1)));
        System.out.println(JsonUtils.convertObjToJsonString(new JumpFloor().jumpFloor(2)));

        System.out.println(JsonUtils.convertObjToJsonString(new JumpFloor().jumpFloor(3)));

        System.out.println(JsonUtils.convertObjToJsonString(new JumpFloor().jumpFloor(4)));

        System.out.println(JsonUtils.convertObjToJsonString(new JumpFloor().jumpFloor(5)));

    }

    int jumpFloor(int target) {

        if (target <= 1) {
            return 1;
        }
        return jumpFloor(target - 1) + jumpFloor(target - 2);

    }

    /**
     * 斐波那契数列  1、1、2、3、5、8、13、21、34
     * @param n
     * @return
     */
    public int Fibonacci(int n) {
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        return Fibonacci(n-2) + Fibonacci(n-1);
    }

}
