package com.ding.study.collection;

import java.util.*;

/**
 * @author daniel 2019-6-25 0025.
 */
public class TestMapMain {


    public static void main(String[] args) throws Exception {
        HashMap<Integer, Integer> map = new HashMap<>();
       map.put(1, 222);
        //如果key存在 就返回以前的value
        System.out.println(map.put(1, 1));
        map.put(2, 2);
        map.put(3, 3);
        map.put(3, 6);
        Set<Integer> set = map.keySet();
        for (Integer i : set) {
            System.out.println("set:" + i);
        }
        System.out.println("containsKey:" + map.containsKey(1));
        System.out.println("containsValue:" + map.containsValue(1));

        Set<Map.Entry<Integer, Integer>> set1 = map.entrySet();

        for (Map.Entry<Integer, Integer> i : set1) {
            System.out.println("getKey:" + i.getKey() + ";--value:" + i.getValue());
        }
        Collection<Integer> arrayList = map.values();
        for (Integer i : arrayList) {
            System.out.println("values:" + i);
        }


        LinkedHashMap<String, String> link = new LinkedHashMap();
        link.put("1", "1");
        link.put("2", "2");
        link.put("3", "3");
        link.put("4", "4");
        Set<Map.Entry<String, String>> link1 = link.entrySet();
        for (Map.Entry<String, String> key : link1) {
            System.out.println("LinkedHashMap getKey:" + key.getKey() + ";--value:" + key.getValue());
        }


        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("3", "three");
        treeMap.put("1", "one");
        treeMap.put("2", "two");
        treeMap.put("4", "four");
        Set<Map.Entry<String, String>> treeSet = treeMap.entrySet();
        for (Map.Entry<String, String> key : treeSet) {
            System.out.println("TreeMap getKey:" + key.getKey() + ";--value:" + key.getValue());
        }

        System.out.println("ceilingKey:" + treeMap.ceilingKey("1.5"));
        Map.Entry<String, String> ceilingEntry = treeMap.ceilingEntry("1.5");
        System.out.println("ceilingEntry:" + ceilingEntry.getKey() + "  value:" + ceilingEntry.getValue());
        System.out.println("treeMap.contains:" + treeMap.containsKey("1.5"));
        System.out.println("treeMap.containsValue:" + treeMap.containsValue("2"));

        NavigableSet<String> navigable = treeMap.descendingKeySet();
        Iterator iterator = navigable.iterator();
        while (iterator.hasNext()) {
            System.out.println("iterator.:" + iterator.next());
        }

        NavigableMap<String, String> navigableMap = treeMap.descendingMap();


        System.out.println(" treeMap.firstKey():" + treeMap.firstKey());
        System.out.println(" treeMap.firstEntry():" + treeMap.firstEntry().getKey() +";"+ treeMap.firstEntry().getValue());
        String test = "12345612345456126789012345645678978907890";

        TreeMap<Character, Integer> treeMap1 = new TreeMap();
        char[] c = test.toCharArray();
        for (char c1 : c) {
            Integer i = treeMap1.get(c1);
            if (i == null) {
                treeMap1.put(c1, 1);
            } else {
                treeMap1.put(c1, ++i);
            }
        }

        Set<Map.Entry<Character, Integer>> set2 = treeMap1.entrySet();
        for (Map.Entry<Character, Integer> temp : set2) {
            System.out.println("出现次数:" + temp.getKey() + "  次数:" + temp.getValue());
        }

        System.out.println("treeMap.floorkey1.5:" + treeMap.floorKey("1.5"));
        Map.Entry<String, String> treeMapFloorEntry = treeMap.floorEntry("1.5");
        System.out.println("treeMapFloorEntry1.5:" + treeMapFloorEntry.getKey() + "  value:" + treeMapFloorEntry.getValue());


        //返回此映射的部分视图，其键值严格小于 toKey。
        SortedMap<String,String> sortedMap = treeMap.headMap("2.5");
        Set<Map.Entry<String,String>> sortedSet = sortedMap.entrySet();
        for (Map.Entry<String,String>temp:sortedSet) {
            System.out.println("Sorted.:" + temp.getKey()+"--"+temp.getValue());
        }

        //higherKey严格大于;lowerKey严格小于;  ceilingKey是大于等于; floorKey小于等于给定键的最大键
        System.out.println("treeMap.higherKey.2:" + treeMap.higherKey("2"));

        System.out.println("treeMap.lastKey:" + treeMap.lastKey());
        System.out.println("treeMap.lastKey:" + treeMap.lastKey());
        System.out.println("treeMap.lowerKey.2:" + treeMap.lowerKey("2"));


        //返回此映射的部分视图，其键大于等于 fromKey。
        SortedMap<String,String> sortedMap1 = treeMap.tailMap("2.5");
        Set<Map.Entry<String,String>> sortedSet1 = sortedMap1.entrySet();
        for (Map.Entry<String,String>temp:sortedSet1) {
            System.out.println("Sorted.tailMap:" + temp.getKey()+"--"+temp.getValue());
        }

        //老版本hashtable
        Hashtable<String,String> hashtable=new Hashtable();
        hashtable.put("","");

    }
}
