package com.ding.study.nowcoder.a15Array_mergeSortArray;

/**
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
