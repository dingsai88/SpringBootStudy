package com.ding.study.datastructure;

import com.ding.study.util.JsonUtils;

/**
 * 直接选择排序:走一遍找到极致，到最后再交换位置
 *
 * @author daniel 2019-8-12 0012.
 * @see <a href="https://blog.csdn.net/u011815404/article/details/79256237">直接选择排序</a>
 */
public class HG62SelectionSort {
    public static void main(String[] arg) {

        int[] i ={8, 5, 2, 6, 9, 3, 1, 4, 0, 7};
        System.out.println(JsonUtils.convertObjToJsonString(i));
        selectionSort(i);
        System.out.println(JsonUtils.convertObjToJsonString(i));

    }

    /**
     * 直接选择排序
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //默认第一个是最小值
            int min = i;
            //找到其它最小的一个
            for (int j = i; j < arr.length; j++) {
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
