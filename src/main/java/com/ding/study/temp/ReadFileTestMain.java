package com.ding.study.temp;

import com.ding.study.util.AESUtil;
import com.google.common.base.Strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

/**
 * 营销获客 IC 文件读取 徐恒 直播
 * @author daniel 2019-7-3 0003.
 */
public class ReadFileTestMain {

    public static void main(String[] args) throws Exception {

        toArrayByFileReader1("D:\\DingSai\\data\\aa.txt");

    }

    /**
     *
     * @param name
     */
    public static void toArrayByFileReader1(String name) {
        try {
            FileReader fr = new FileReader(name);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            Map<String, Integer> map = new HashMap<String, Integer>();
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
               // System.out.println(str+"   解密后:"+ AESUtil.aesDecrypt(URLDecoder.decode(str, "UTF-8"), "DmhzsbcNSDingSaizaMDRz5EJcZPQ=="));
                System.out.println(""+str);

              //  System.out.println(AESUtil.aesDecrypt(URLDecoder.decode(str, "UTF-8"), "123456"));
          /*           String[] array = str.split("\\s+");
                for (String s : array) {
                    System.out.println("输出:"+s+";");
               if (map.containsKey(s)) {//查看字符是否在map的key中存在，如果存在
                        Integer old = map.get(s);//通过key获取value的值
                        map.put(s, old + 1);//把字符放入map的key中，value设置为通过key获取value的值+1
                    } else {//查看字符是否在map的key中存在，如果不存，把字符放入map的key中，value默认设置为1
                        map.put(s, 1);
                    }
                }*/

            }
            bf.close();
            fr.close();
            Map<String, Integer> tmap = new TreeMap<String, Integer>();


            for(String key : map.keySet()){
                if(key.length()>9|| Strings.isNullOrEmpty(key.trim())){
                    continue;
                }
                tmap.put(key,map.get(key));
                System.out.println(key+","+map.get(key));
            }

            tmap = sortByValueDescending(tmap);
            for (Map.Entry<String, Integer> entry : tmap.entrySet()) {
                System.out.println("号码:" + entry.getKey() + ",次数:" + entry.getValue());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //降序排序
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescending(Map<K, V> map)
    {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
            {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return -compare;
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    //升序排序
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueAscending(Map<K, V> map)
    {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
            {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return compare;
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }


}
