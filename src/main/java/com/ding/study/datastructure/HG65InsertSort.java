package com.ding.study.datastructure;

import com.ding.study.util.JsonUtils;

/**
 * @author daniel 2019-8-13 0013.
 */
public class HG65InsertSort {

    public static void main(String[] args) {
        int[] arr = {3,1,5,7,2,4,9,6};
        System.out.println("直接插入排序算法：排序前:" + JsonUtils.convertObjToJsonString(arr));
        insertSort(arr);
        System.out.println("直接插入排序算法：排序后:" + JsonUtils.convertObjToJsonString(arr));
    }

    /**
     * 　0.初始状态 3，1，5，7，2，4，9，6（共8个数）
     　　   有序表：3；无序表：1，5，7，2，4，9，6
     　　1.第一次循环，从无序表中取出第一个数 1，把它插入到有序表中，使新的数列依旧有序
     　　   有序表：1，3；无序表：5，7，2，4，9，6
     　　2.第二次循环，从无序表中取出第一个数 5，把它插入到有序表中，使新的数列依旧有序
     　　   有序表：1，3，5；无序表：7，2，4，9，6
     　　3.第三次循环，从无序表中取出第一个数 7，把它插入到有序表中，使新的数列依旧有序
     　　   有序表：1，3，5，7；无序表：2，4，9，6
     　　4.第四次循环，从无序表中取出第一个数 2，把它插入到有序表中，使新的数列依旧有序
     　　   有序表：1，2，3，5，7；无序表：4，9，6
     　　5.第五次循环，从无序表中取出第一个数 4，把它插入到有序表中，使新的数列依旧有序
     　　   有序表：1，2，3，4，5，7；无序表：9，6
     　　6.第六次循环，从无序表中取出第一个数 9，把它插入到有序表中，使新的数列依旧有序
     　　   有序表：1，2，3，4，5，7，9；无序表：6
     　　7.第七次循环，从无序表中取出第一个数 6，把它插入到有序表中，使新的数列依旧有序
     　　   有序表：1，2，3，4，5，6，7，9；无序表：（空）
     * @param a
     */
    public static void insertSort(int a[]) {
        //第一个是有序序列；第二个下标为1是无须序列。
        for (int i = 1; i < a.length; i++) {
            //有序序列的最后一个
            int j = i - 1;
            //要从无序序列拿第一个数
            int temp = a[i];
            for (; j >= 0 && a[j] > temp; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = temp;

        }


    }


}
