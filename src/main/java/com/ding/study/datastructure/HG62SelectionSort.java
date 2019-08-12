package com.ding.study.datastructure;

import com.ding.study.util.JsonUtils;

/**
 * 直接选择排序:
 *
 * @author daniel 2019-8-12 0012.
 */
public class HG62SelectionSort {
    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        System.out.println("直接选择排序：排序前:" + JsonUtils.convertObjToJsonString(arr));
        selectionSort(arr);
        System.out.println("直接选择排序：排序后:" + JsonUtils.convertObjToJsonString(arr));
    }

    /**
     * 直接选择排序
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length ; i++) {
            //默认第一个是最小值
            int min = i;
            //找到其它最小的一个
            for (int j = i; j < arr.length ; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            //如果最小的一个不是初始化本身的-就替换
            if (min != i) {
                int t = arr[i];
                arr[i] = arr[min];
                arr[min] = t;
            }
        }
    }
}
