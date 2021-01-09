package com.ding.study.nowcoder.a12twoSum;

import com.ding.study.util.JsonUtils;

import java.util.HashMap;

/**
 * 题目描述
 * 给出一个整数数组，请在数组中找出两个加起来等于目标值的数，
 * 你给出的函数twoSum 需要返回这两个数字的下标（index1，index2），需要满足 index1 小于index2.。注意：下标是从1开始的
 * 假设给出的数组中只存在唯一解
 * 例如：
 * 给出的数组为 {20, 70, 110, 150},目标值为90
 * 输出 index1=1, index2=2
 *
 * https://www.nowcoder.com/practice/20ef0972485e41019e39543e8e895b7f?tpId=190&&tqId=35361&rp=1&ru=/ta/job-code-high-rd&qru=/ta/job-code-high-rd/question-ranking
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a=new int[]{3,2,4};
        System.out.println(JsonUtils.convertObjToJsonString(solution.twoSum(a,6)));
        System.out.println(JsonUtils.convertObjToJsonString(solution.twoSum2(a,6)));

    }

    public int[] twoSum (int[] numbers, int target) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<numbers.length;i++){
            if(map.containsKey(target-numbers[i])){
                return  new int[]{map.get(target-numbers[i])+1,i+1};
            }else {
                map.put(numbers[i],i);
            }
        }

        return null;
    }


    public int[] twoSum2 (int[] numbers, int target) {
        // write code here
        for(int i=0;i<numbers.length;i++){
            for(int j=i+1;j<numbers.length;j++){
                if((numbers[i]+numbers[j])==target){
                    return new int[]{i+1,j+1};
                }
            }

        }

        return new int[]{-1,-1};

    }
}
