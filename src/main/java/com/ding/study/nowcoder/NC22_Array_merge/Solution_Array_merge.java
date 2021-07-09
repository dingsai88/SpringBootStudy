package com.ding.study.nowcoder.NC22_Array_merge;

import com.ding.study.util.JsonUtils;

/**
 *NC22 合并两个有序的数组
 *
 *描述
 * 给出一个整数数组 和有序的整数数组 ，请将数组 合并到数组 中，变成一个有序的升序数组
 * 注意：
 * 1.可以假设 数组有足够的空间存放 数组的元素， 和 中初始的元素数目分别为 和 ，的数组空间大小为 +
 * 2.不要返回合并的数组，返回是空的，将数组 的数据合并到里面就好了
 * 3.数组在[0,m-1]的范围也是有序的
 *
 * 例1:
 * A: [1,2,3,0,0,0]，m=3
 * B: [2,5,6]，n=3
 * 合并过后A为:
 * A: [1,2,2,3,5,6]
 *
 *
 *示例1
 * 输入：
 * [4,5,6],[1,2,3]
 * 复制
 * 返回值：
 * [1,2,3,4,5,6]
 *
 *
 *
 *
 *
 *
 *
 *
 * @author daniel 2021-7-9 0009.
 */
public class Solution_Array_merge {
    public void merge(int A[], int m, int B[], int n) {
        int currentA=m-1;
        int currentB=n-1;
        int idxAll=m+n-1;
        while(currentA>=0&&currentB>=0){
            if(A[currentA]>B[currentB]){
                A[idxAll--]=A[currentA--];
            }else {
                A[idxAll--]=B[currentB--];
            }
        }

        while(currentA>=0){
                A[idxAll--]=A[currentA--];
        }
        while(currentB>=0){
            A[idxAll--]=B[currentB--];
        }

    }


    public static void main(String[] args) {

        Solution_Array_merge bean = new Solution_Array_merge();

        int A[] = new int[]{1, 2, 2, 3, 4, 0, 0, 0};
        int B[] = new int[]{1, 3, 3, 0, 0, 0, 0, 0};
        System.out.println(JsonUtils.convertObjToJsonString(A));
        System.out.println(JsonUtils.convertObjToJsonString(B)+"\n\n");
        bean.merge(A, 5, B, 3);

        System.out.println(JsonUtils.convertObjToJsonString(A));
        System.out.println(JsonUtils.convertObjToJsonString(B)+"\n\n");



    }
}
