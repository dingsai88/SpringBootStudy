package com.ding.study.datastructure;

import java.util.Arrays;

/**
 * @author daniel 2019-8-12 0012.
 * @see <a href="https://www.jianshu.com/p/0d383d294a80">堆排序</a>
 * 堆排序
 * 堆排序的基本思想：
1.首先将待排序的记录序列用完全二叉树表示；
2.然后完全二叉树构造成一个堆，此时，选出了堆中所有记录关键字的最小者;与两个儿子较小的交换，相同的时顶最小和左儿子，顶最大和右儿子。
3.最后将关键字最小者从堆中移走，并将剩余的记录再调整成堆，这样又找出了次小的关键字记录，以此类推，直到堆中只有一个记录;

 */
public class HG63HeapSort {


    public static void main(String[] args) {
        int[] arr = {7, 23, 45, 9, 40, 73, 12, 49};  //0下标放的是数组长度，
        System.out.print("堆排序开始：" + Arrays.toString(arr));
        heapSort(arr);
        System.out.print("堆排序结束：" + Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        //对数组进行建堆操作，就是从最后一个非叶结点进行筛选的过程
        for (int i = (arr.length - 1) / 2; i > 0; i--) {
            //因为0没有使用，所以length-1
            heapAdjust(arr, i, arr.length - 1);
        }
        for (int i = arr.length - 1; i > 1; i--) {
            int temp = arr[1];
            arr[1] = arr[i];
            arr[i] = temp;
            heapAdjust(arr, 1, i - 1);
        }
    }


    public static void heapAdjust(int[] arr, int s, int m) {
        //已知arr[s...m]中记录的关键字除arr[s]之外均满足堆的定义，本函数调整arr[s]的关键字，使arr[s...m]成为一个最大堆
        int rc = arr[s]; //s是最后一个非叶子节点
        for (int j = 2 * s; j <= m; j = s * 2) {
            if (j < m && arr[j] < arr[j + 1]) {
                //j为key较大的下标
                j++;
            }
            if (rc >= arr[j]) {
                break;
            }
            arr[s] = arr[j];  //上移到父节点
            s = j;
        }
        arr[s] = rc;  //要放入的位置
    }


}
