package com.ding.study.datastructure;

import com.ding.study.util.JsonUtils;

/**
 * @author daniel 2019-8-9 0009.
 * @see <a href="https://blog.csdn.net/u014241071/article/details/81565148">快速排序说明</a>
 */
public class HG62QuickSort {

    public static void main(String[] args) {
        // int []arr={9,8,7,6,5,4,3,2,1};
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        System.out.println("快速排序：排序前:" + JsonUtils.convertObjToJsonString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序：排序后:" + JsonUtils.convertObjToJsonString(arr));


    }

    /**
     * 快速排序
     * 递归
     *
     * @param arr
     * @param left  递归的左端
     * @param right 递归的右端
     */
    public static void quickSort(int[] arr, int left, int right) {
        //递归出口
        if (left >= right) {
            return;
        }

        int tempLeft = left;
        int tempRight = right;
        //每次的base mark基准值，从这个值比较
        int base = arr[left];
        //当两点重合时
        while (tempLeft < tempRight) {
            //从右往左找小于mark的
            while (base <= arr[tempRight] && tempLeft < tempRight) {
                tempRight--;
            }
            //从左往右找大于mark的
            while (base >= arr[tempLeft] && (tempLeft < tempRight)) {
                tempLeft++;
            }
            //如果两头的指针不重合，就交换左边找到的大于base的值和右边找到小于base的值
            if (tempLeft < tempRight) {
                int tempSwap = arr[tempLeft];
                arr[tempLeft] = arr[tempRight];
                arr[tempRight] = tempSwap;
            }
        }
        //左边最后找到的小于的值，放到最左端
        arr[left] = arr[tempLeft];
        //比较完成把最左边的base值放到左边的最右边
        arr[tempLeft] = base;
        //递归左半边
        quickSort(arr, left, tempLeft - 1);
        //递归右半边
        quickSort(arr, tempLeft + 1, right);

    }
}
