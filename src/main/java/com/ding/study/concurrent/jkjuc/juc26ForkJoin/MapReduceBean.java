package com.ding.study.concurrent.jkjuc.juc26ForkJoin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

/**
 * @author daniel 2019-11-8 0008.
 */
public class MapReduceBean extends
        RecursiveTask<Map<String, Long>> {
    private String[] fc;
    private int start, end;

    //构造函数
    MapReduceBean(String[] fc, int fr, int to) {
        this.fc = fc;
        this.start = fr;
        this.end = to;
    }

    @Override
    protected Map<String, Long> compute() {
        if (end - start == 1) {
            return calc(fc[start]);
        } else {
            int mid = (start + end) / 2;
            MapReduceBean mr1 = new MapReduceBean(fc, start, mid);
            mr1.fork();
            MapReduceBean mr2 = new MapReduceBean(fc, mid, end);
            //计算子任务，并返回合并的结果
            Map<String, Long> result=  merge(mr2.compute(), mr1.join());
            return result;
        }
    }

    //合并结果
    private Map<String, Long> merge(Map<String, Long> r1, Map<String, Long> r2) {
        Map<String, Long> result = new HashMap<>();
        result.putAll(r1);
        //合并结果
        r2.forEach((k, v) -> {
            Long c = result.get(k);
            if (c != null) {
                result.put(k, c + v);
            } else {
                result.put(k, v);
            }
        });
        return result;
    }

    /**
     * 统计单词数量
     * @param line
     * @return
     */
    private Map<String, Long> calc(String line) {
        Map<String, Long> result = new HashMap<>();
        //分割单词
        String[] words = line.split("\\s+");
        //统计单词数量
        for (String w : words) {
            Long v = result.get(w);
            if (v != null) {
                result.put(w, v + 1);
            } else {
                result.put(w, 1L);
            }
        }
        return result;
    }
}