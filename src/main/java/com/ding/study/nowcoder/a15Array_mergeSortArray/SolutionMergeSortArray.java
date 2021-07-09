package com.ding.study.nowcoder.a15Array_mergeSortArray;

/**
 * NC22 合并两个有序的数组
 *
 * 描述
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
 * 两个有序数据的合并
 */
public class SolutionMergeSortArray {

    public void merge(int A[], int m, int B[], int n) {
        int curA=m-1;
        int curB=n-1;
        int idx=m+n-1;
        while(curA>=0&&curB>=0){
            if(A[curA]>B[curB]){
                A[idx--]=A[curA--];
            }else{
                A[idx--]=B[curB--];
            }

        }

        while(curA>=0){
            A[idx--]=A[curA--];
        }

        while(curB>=0){
            A[idx--]=B[curB--];
        }
    }


}
