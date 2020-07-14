package com.ding.study.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.jianshu.com/p/fc5c9cdf3aab
 * I.Gson的构造方法
 *1.new Gson()
 *
 *2.new GsonBuilder
 *
 *
 * @author daniel 2020-7-14 0014.
 */
public class GsonUtilTest {



    public static void main(String[] args) throws Exception {
        //泛型用法
         Type listType = new TypeToken<List<String>>() {}.getType();
         List<String> target = new LinkedList<String>();
         target.add("blah");
         Gson gson = new Gson();
         String json = gson.toJson(target, listType);
         List<String> target2 = gson.fromJson(json, listType);
        
        

    }
}
