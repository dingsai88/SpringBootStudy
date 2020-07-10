package com.ding.study.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import com.ding.study.temp.TestBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author daniel 2019-6-14 0014.
 */
public class JsonUtils {

    private static Gson gson = new GsonBuilder().create();

    public static Gson getGson() {
        return gson;
    }

    /**
     * 将一个json字符串转换为指定类型的对象
     *
     * @param jsonString
     * @return
     * @throws IOException
     */
    public static <T> T convertJsonStringToObj(String jsonString,
                                               Class<T> tClass) {
        return gson.fromJson(jsonString, tClass);
    }

    /**
     * 将一个对象转换成json字符串
     *
     * @param o
     * @return
     */
    public static String convertObjToJsonString(Object o) {
        return gson.toJson(o);
    }


    public static void main(String[] args) throws Exception {

        Type type = new TypeToken<List<TestBean>>() {
        }.getType();
        List<TestBean> resp = new Gson().fromJson("dddddddddd", type);
        System.out.println("fanxing");

    }
}
