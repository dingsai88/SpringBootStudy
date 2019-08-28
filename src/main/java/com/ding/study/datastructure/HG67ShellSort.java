package com.ding.study.datastructure;

import com.ding.study.util.JsonUtils;

/**
 * 希尔排序--插入排序的优化，先分成N/2组，每组进行插入排序；再来N/2/2组进行排序，最后按照1分组，等于全部插入排序
 * @see <a href="https://www.jianshu.com/p/40dcc3b83ddc">希尔排序<a/>
 * @see <a href="https://blog.csdn.net/qq_39207948/article/details/80006224">希尔排序<a/>
 *
 * @author daniel 2019-8-14 0014.
 */
public class HG67ShellSort {
    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        System.out.println("希尔排序：排序前:" + JsonUtils.convertObjToJsonString(arr));
        shellSort(arr);
        System.out.println("希尔排序：排序后:" + JsonUtils.convertObjToJsonString(arr));
    }
    public static void shellSort(int[] a){
        //增量长度
        int gap = a.length;
        int dk,sentinel,k;
        while(true){
            //逐渐减小增量长度
            gap = (int)Math.ceil(gap/2);
            //确定增量长度
            dk = gap;
            for(int i=0;i<dk;i++){
                //用增量将序列分割，分别进行直接插入排序。随着增量变小为1，最后整体进行直接插入排序
                for(int j=i+dk;j<a.length;j = j+dk){
                    k = j-dk;
                    sentinel = a[j];
                    while(k>=0 && sentinel<a[k]){
                        a[k+dk] = a[k];
                        k = k-dk;
                    }
                    a[k+dk] = sentinel;
                }
            }
            //当dk为1的时候，整体进行直接插入排序
            if(dk==1){
                break;
            }
        }
    }
}
