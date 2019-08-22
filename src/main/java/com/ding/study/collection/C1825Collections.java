package com.ding.study.collection;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 工具栏
 *
 * @author daniel 2019-8-20 0020.
 */
public class C1825Collections {
    public static void main(String[] args) {
        //二进制向左移一位；相当于除以2
        System.out.println(10 >> 1);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(4);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(6);


        //[7, 4, 1, 2, 3, 5, 6]
        System.out.println("正常顺序:" + list);
        Collections.sort(list);
        //[1, 2, 3, 4, 5, 6, 7]
        System.out.println("排序后:" + list);
        //搜索不到的时候返回最大索引+1(最大下标+1再+1)    return -(low计算后大于最大下标1后再 + 1); 返回-8
        System.out.println("二分查找:" + Collections.binarySearch(list, 99));
       //max:7
        System.out.println("max:" + Collections.max(list));
        //翻转
        Collections.reverse(list);
        //[7, 6, 5, 4, 3, 2, 1]
        System.out.println("reverse翻转:" + list);
        //洗牌
        Collections.shuffle(list);
        //[6, 5, 2, 4, 7, 3, 1]
        System.out.println("shuffle洗牌:" + list);

        "".hashCode();
    }
}
