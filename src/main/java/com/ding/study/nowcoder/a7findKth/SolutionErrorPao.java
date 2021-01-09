package com.ding.study.nowcoder.a7findKth;

/**
 * 题目描述
 * 有一个整数数组，请你根据快速排序的思路，找出数组中第K大的数。
 *
 * 给定一个整数数组a,同时给定它的大小n和要找的K(K在1到n之间)，请返回第K大的数，保证答案存在。
 */
public class SolutionErrorPao {

    public static void main(String[] args) {
        int[] i = {1, 3, 5, 2, 2};
        System.out.println(new SolutionErrorPao().findKth(i,5, 3));

    }

    public int findKth(int[] a, int n, int K) {
        // write code here
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(a[i]<a[j]){
                    int t=a[i];
                    a[i]=a[j];
                    a[j]=t;
                }
            }

        }

        return a[K-1];

    }
}
