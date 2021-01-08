package com.ding.study.nowcoder.a4lru;

import com.ding.study.util.JsonUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author daniel 2021-1-8 0008.
 */
public class SolutionSelf {

    public int[] LRU(int[][] operators, int k) {
        // write code here
        LRUCache cache = new LRUCache(k);
        ArrayList<Integer> list = new ArrayList();
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 1) {
                cache.put(operators[i][1], operators[i][2]);
            } else if (operators[i][0] == 2) {
                if (cache.get(operators[i][1]) != null) {
                    list.add(Integer.parseInt(cache.get(operators[i][1]).toString()));
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
        int[][] input = new int[][]{ {1, 1, 1}, {1, 2, 2}, {1, 3, 2},   {2, 1}, {1,4,4},{2,2}};
        System.out.println(JsonUtils.convertObjToJsonString(solution.LRU(input, 3)));


    }


    class LRUCache extends LinkedHashMap {
        private int max;

        public LRUCache(int max) {
            super(16,0.75f,true);
            this.max = max;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > max;
        }

    }
}
