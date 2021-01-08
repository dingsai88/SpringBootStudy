package com.ding.study.nowcoder.a4lru;

import com.ding.study.util.JsonUtils;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author daniel 2021-1-8 0008.
 */
public class SolutionSelf {

    public int[] LRU(int[][] operators, int k) {
        // write code here
        LRUCache<Integer,Integer> cache = new LRUCache(k);
        ArrayList<Integer> list = new ArrayList();
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 1) {
                cache.put(operators[i][1], operators[i][2]);
            } else if (operators[i][0] == 2) {
                if (cache.get(operators[i][1]) != null) {
                    list.add(cache.get(operators[i][1]));
                } else {
                    list.add(-1);
                }
            }


        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = (int) list.get(i);
        }

        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SolutionSelf solution = new SolutionSelf();
        //int[][] input = new int[][]{ {1, 1, 1}, {1, 2, 2}, {1, 3, 2},   {2, 1}, {1,4,4},{2,2}};
        int[][] input = new int[][]{{1, 1, 1}, {1, 2, 2}, {1, 3, 2}, {2, 1}, {1, 4, 4}, {2, 2}};
        System.out.println(JsonUtils.convertObjToJsonString(solution.LRU(input, 3)));


        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
      // LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1, 1);
        linkedHashMap.put(2, 2);
        linkedHashMap.put(5, 5);
        linkedHashMap.put(7, 7);
        linkedHashMap.put(9, 9);
        System.out.println("输出:map:" +linkedHashMap);
        linkedHashMap.get(2);
        linkedHashMap.get(5);
        System.out.println("输出:map2:" +linkedHashMap);
       for (Map.Entry<Integer, Integer> map : linkedHashMap.entrySet()) {
            System.out.println("输出:map:" + map.getKey() + ":" + map.getValue());
        }

    }


    class LRUCache<K,V> extends LinkedHashMap<K,V> {
        private int max;

        public LRUCache(int max) {
            super(16, 0.75f, true);
            this.max = max;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
            return size() > max;
        }

    }
}
