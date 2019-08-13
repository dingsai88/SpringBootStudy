package com.ding.study.datastructure;

import com.ding.study.util.JsonUtils;

/**
 * @author daniel 2019-8-13 0013.
 */
public class HG66HalfInsertSort {
    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 7, 2, 4, 9, 6};
        System.out.println("直接插入排序算法：排序前:" + JsonUtils.convertObjToJsonString(arr));
        halfInsert(arr);
        System.out.println("直接插入排序算法：排序后:" + JsonUtils.convertObjToJsonString(arr));
    }

    public static void halfInsert(int[] a) {
        for (int i = 1; i < a.length; i++) {
            //无序序列第一个值，需要插入的值
            int temp = a[i];
            int low = 0;
            //有序序列第一个值
            int high = i - 1;

            while (low <= high) {
                //在有序序列中间开始找
                int m = (low + high) / 2;
                //如果比中间的元素小，则插入位置在低半区
                if (a[m] > temp) {
                    high = m - 1;
                } else {
                    //否则，插入位置在高半区
                    low = m + 1;
                }
            }
            //把从low开始以后或high+1以后的元素向后移动，插入
            for (int j = i; j > low; j--) {
                //移动元素
                a[j] = a[j - 1];
            }
            a[low] = temp;//插入在合适的位置
        }
    }
}
