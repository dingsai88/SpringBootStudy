package com.ding.study.collection;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author daniel 2019-6-21 0021.
 */
public class TestCollectionSetMain {

    private static void test(){

        //set 不重复的元素，只能有一个null
        //HashMap核心实现
        HashSet<String > hashSet = new HashSet();
        //比较hashcode，不相同就添加，相同就比较equals,String 就不替换int就替换。因为String重写了hashcode和equals方法
        hashSet.add("2");
        hashSet.add("3");
        hashSet.add("2");
        System.out.println(hashSet);
        TreeSet treeSet = new TreeSet();
        treeSet.add(33);

        /////////////////先对比hashCode如果相同再对比equals有一个不同再添加
        HashSet<Student > studentHashSet = new HashSet();
        Student s1=new Student("1","a");
        Student s2=new Student("2","b");
        Student s3=new Student("3","c");
        studentHashSet.add(s1);
        studentHashSet.add(s2);
        studentHashSet.add(s3);
        System.out.println(studentHashSet);

        //对替换value 不会替换KEY
        HashMap<Student ,String> map = new HashMap();
        map.put(s1,"a1");
        map.put(s2,"a2");
        map.put(s3,"a3");
        System.out.println(map);

        //具有可预知迭代顺序的 Set 接口的哈希表和链接列表实现。 底层是LinkedHashMap
        LinkedHashSet<String> linkedHashSet=new LinkedHashSet();
        linkedHashSet.add("1");
        linkedHashSet.add("4");
        linkedHashSet.add("2");
        linkedHashSet.add("3");
        linkedHashSet.add("1");
        System.out.println("插入顺序的不可重复："+linkedHashSet);



        //里边实现是 TreeMap;必须实现Comparable比较器接口
        TreeSet treeSet1=new TreeSet();
        treeSet1.add(1);
        treeSet1.add(5);
        treeSet1.add(4);
        treeSet1.add(3);
        treeSet1.add(6);
        treeSet1.add(2);
        treeSet1.add(4);
        // NavigableMap map1=new NavigableMap();

        System.out.println("TreeSet自然排序:"+treeSet1);
    }

    public static void main(String[] args) {
        test2();
        linkedHashSet();
        treeSet();
        treeSetBean();
        arrayBlockingQueue();
    }



    private static void arrayBlockingQueue(){
        ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(1);
        arrayBlockingQueue.add(3);
        arrayBlockingQueue.peek();

    }




    private static void treeSetBean(){
        Student s1=new Student("1",1);
        Student s4=new Student("4",4);
        Student s2=new Student("2",2);
        Student s6=new Student("6",6);
        Student s3=new Student("3",3);
        TreeSet<Student> set=new TreeSet<>( );
        set.add(s1);
        set.add(s2);
        set.add(s3);
        set.add(s6);
        set.add(s4);

        for(Student str:set){
            System.out.println("treeSetBean:"+str);
        }
        System.out.println(" set.comparator():"+ set.comparator());

    }
    private static void treeSet(){
        TreeSet<String> set=new TreeSet<>();
        set.add("31");
        set.add("1");
        set.add("21");
      ;
        set.add("41");
        for(String str:set){
            System.out.println("treeSet:"+str);
        }

    }

    private static void linkedHashSet(){
        LinkedHashSet<String> set=new LinkedHashSet<>();
        set.add("1");
        set.add("21");
        set.add("31");
        set.add("41");
        for(String str:set){
            System.out.println("linkedHashSet:"+str);
        }

    }





    private static void test2(){
        HashSet<String> set=new HashSet<>();
        set.add("1");
        set.add("21");
        set.add("31");
        set.add("31");
        for(String str:set){
            System.out.println("str:"+str);
        }

    }


}
