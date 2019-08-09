package com.ding.study.datastructure;

import com.ding.study.util.JsonUtils;

/**第一次跑把最小的冒到最后，
 * 第二次跑把第二小的冒泡到倒数第二个位置。
 * @author daniel 2019-8-9 0009.
 */
public class HG61BubbleSort {
    public static void main(String[] args) {
        int[] bubbleSort = {2, 5, 7,6, 4, 3};
        System.out.println("冒泡开始:" + JsonUtils.convertObjToJsonString( bubbleSort));
        for (int i = 0; i < bubbleSort.length - 1; i++) {
            for (int j = 0; j < (bubbleSort.length - 1-i); j++) {
                if (bubbleSort[j] < bubbleSort[j + 1]) {
                    int temp = bubbleSort[j + 1];
                    bubbleSort[j + 1] = bubbleSort[j];
                    bubbleSort[j] = temp;
                }
            }
        }
        System.out.println("冒泡结束:" + JsonUtils.convertObjToJsonString( bubbleSort));
    }
}
