package com.ding.study.nowcoder.a4Link_lru;

import com.ding.study.util.JsonUtils;

import java.util.ArrayList;

/**
 * @author daniel 2021-1-8 0008.
 */
public class Solution {

    /**
     * lru design
     *
     * @param operators int整型二维数组 the ops
     * @param k         int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU(int[][] operators, int k) {
        LRUCache<Integer, Integer> lru = new LRUCache<>(k);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 1) {
                lru.put(operators[i][1], operators[i][2]);
            } else if (operators[i][0] == 2) {
                if (lru.get(operators[i][1]) != null) {
                    list.add(lru.get(operators[i][1]));
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
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
       // int[][] input = new int[][]{{1, 4, 4},{1, 1, 1}, {1, 2, 2},{2, 2}, {1, 3, 3}, {2, 1},  {2, 2},  {2, 4},  {2, 4},  {2, 4}};
        int[][] input = new int[][]{ {1, 1, 1}, {1, 2, 2}, {1, 3, 2},   {2, 1}, {1,4,4},{2,2}};
        System.out.println(JsonUtils.convertObjToJsonString(solution.LRU(input, 3)));
    }


}
