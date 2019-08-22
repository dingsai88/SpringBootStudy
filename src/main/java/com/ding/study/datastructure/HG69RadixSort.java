package com.ding.study.datastructure;

import com.ding.study.util.JsonUtils;

/**
 *
 * @see <a href="https://blog.csdn.net/wmh1152151276/article/details/87885912">基数排序</a>
 * @author daniel 2019-8-14 0014.
 */
public class HG69RadixSort {

    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        System.out.println("基数排序：排序前:" + JsonUtils.convertObjToJsonString(arr));
        radixSort(arr);
        System.out.println("基数排序：排序后:" + JsonUtils.convertObjToJsonString(arr));

    }
    public static void radixSort(int[] array) {
        int max = getMax(array);
        int bit = 1;
        while(max / bit > 0) {
            radix(array, bit);
            bit *= 10;
        }
    }
    public static  void radix(int[] array, int bit) {
        int[] temp = new int[array.length];
        int[] bucket = new int[10];
        for(int i = 0; i < array.length; i++) {
            bucket[(array[i] / bit) % 10]++;
        }
        for(int i = 1; i < bucket.length; i++) {
            bucket[i] += bucket[i-1];
        }
        for(int i = array.length - 1; i >= 0; i--) {
            temp[bucket[(array[i] / bit) % 10] - 1] = array[i];
            bucket[(array[i] / bit) % 10]--;
        }
        for(int i = 0; i < temp.length; i++) {
            array[i] = temp[i];
        }
    }

    public static  int getMax(int[] array) {
        int max = array[0];
        for(int i = 1; i < array.length; i++){
            if(array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }







}
