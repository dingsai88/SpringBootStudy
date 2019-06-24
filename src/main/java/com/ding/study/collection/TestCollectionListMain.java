package com.ding.study.collection;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author daniel 2019-6-20 0020.
 */
public class TestCollectionListMain {

    public static boolean compare(int[] arr1, int[] arr2) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int a : arr1) {
            list1.add(a);
        }
        for (int b : arr2) {
            list2.add(b);
        }
        System.out.println(list1.retainAll(list2));
        //list1中6，5不在list2中执行该方法时进行了移除操作返回true，如果将arr1改为{1,2,3,4}，执行该方法无需进行移除操作返回false；
        return list1.retainAll(list2);

    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 6, 5};
        int[] arr2 = {1, 2, 3, 4, 53};
        boolean b = compare(arr1, arr2);
        System.out.println(".....:" + b);
        System.out.println(".....:\n\n");
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        list1.add(1);
        for (int i : arr1) {
            list1.add(i);
        }
        System.out.println("a:"+list1);
        //  Collections.
        ListIterator iterator = list1.listIterator();
        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            System.out.println(integer);
            if (integer == 1) {
                iterator.remove();
            }
        }
        System.out.println("b:"+list1);
        ArrayList<String> listTemp = new ArrayList<>();
        listTemp.add("a");
        listTemp.add("b");
        listTemp.add("3");
        listTemp.add("1");
        listTemp.add("1");
        System.out.println( "test:"+listTemp.contains("1"));
        //当有多个相同对象时，只能删除其中的一个
        listTemp.remove("1");
        System.out.println("2:" + listTemp);
        AtomicInteger a = new AtomicInteger(2);
        a.incrementAndGet();
        //  System.out.println(".....:" + list1.retainAll(list2));
        for (int i : list1) {
            System.out.println(".....11:" + i);
        }
        for (int i : list2) {
            System.out.println(".....22:" + i);
        }
        System.out.println(".....22:" + (15 >> 1));
        //ArrayList默认10
        //每次增加N+(N>>1);  N>>1  N除以2
        list1.add(1, 44);

        Vector<Integer> vector = new Vector();
        //Vector默认10
        vector.add(1);
        //每次增加N+N
        Collections.synchronizedList(list1);

        //双向循环列表
        LinkedList linkedList = new LinkedList();
        //新增删除快
        linkedList.offerLast(1);
        //下标查询慢;循序访问速度快
        linkedList.add(1, 1);
       // 将指定元素添加到此列表的末尾（最后一个元素）。和add一样
        linkedList.push(22);
         //将此 ArrayList 实例的容量调整为列表的当前大小。应用程序可以使用此操作来最小化 ArrayList 实例的存储量。
        listTemp.trimToSize();

      //  Collections.synchronizedList(linkedList);
        System.out.println(".....2112:" + linkedList);

        System.out.println(".....2112:" + linkedList.poll());
        System.out.println(".....2112:" + linkedList.pop());
        System.out.println(".....2112:" + linkedList);
    }

}
