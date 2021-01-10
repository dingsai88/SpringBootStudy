package com.ding.study.nowcoder.a4Link_lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author daniel 2021-1-8 0008.
 */
public class LRUCache <K, V> extends LinkedHashMap<K, V> {
    //容量
    private int max;

    public LRUCache(int max) {
        //调用父类LinkedHashMap的构造方法，true表示实现访问有序
        super(16, 0.75f, true);
        this.max = max;
    }

    //重写删除策略，当结点数量大于容量，启用删除
     protected boolean removeEldestEntryTT(Map.Entry<K, V> eldest) {
        return size() > max;
    }



}

