package com.ding.study.collection;

import com.ding.study.util.JsonUtils;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * collection学习，他实现的接口是Iterator
 * @author daniel 2019-6-24 0024.
 */
public class TestCollectionMain {


    public static void main(String[] args) {
        //最顶层是迭代器
        Iterable iterable=new ArrayList();
        //List、set、Queue
        Collection collection=new ArrayList();
        //可重复
        List list=new ArrayList();
        //不可重复
        Set set=new HashSet<>();
        //
        Queue queue=new LinkedBlockingQueue();

        //add添加
        collection.addAll(list);
        collection.clear();
        //是不是全部包含
        collection.containsAll(list);
        //是否为空
        collection.isEmpty();
        //迭代器
        Iterator iterator= collection.iterator();
        //全部删除
        collection.removeAll(list);
        //交集
        collection.retainAll(collection);
        ArrayList arrayList=getData();
        System.out.println(arrayList);
        arrayList.remove("2");
        ArrayList t=new ArrayList<Object>();
        t.add("2");
        arrayList.removeAll(t);
        System.out.println(arrayList.toString());
        String[] desc = new String[]{};
        Object[] listStr = arrayList.toArray(new String[0]);
        System.out.println("2:"+ JsonUtils.convertObjToJsonString(desc));
        System.out.println("3:"+JsonUtils.convertObjToJsonString(listStr));
         next(arrayList);
    }

    private static ArrayList next(List list){
        Iterator iterator=list.iterator();
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        iterator.remove();



        return null;
    }

    private static ArrayList getData(){
        ArrayList arrayList=new ArrayList();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("3");
        return arrayList;
    }
}
