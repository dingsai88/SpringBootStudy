package com.ding.study.leetcode;

import java.util.Arrays;

/**
 * @author daniel 2018-10-24 0024.
 */
public class Study26 {

    public static void main(String[] args) {
        Study26 temp = new Study26();
        int[] nums={0,0,1,1,1,2,2,3,3,4};


        System.out.println("原始"+Arrays.toString(nums));
        System.out.println(temp.removeDuplicates(nums));
        System.out.println("最终"+Arrays.toString(nums));
    }


    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        System.out.println("neibu:"+ Arrays.toString(nums));
        return i + 1;
    }





}
