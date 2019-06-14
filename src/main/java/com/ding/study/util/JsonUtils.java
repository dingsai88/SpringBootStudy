package com.ding.study.util;

import java.io.IOException;
import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        T re;

        re = gson.fromJson(jsonString, tClass);

        return re;
    }

    /**
     * 将一个对象转换成json字符串
     *
     * @param o
     * @return
     */
    public static String convertObjToJsonString(Object o) {
        String re = null;

        re = gson.toJson(o);

        return re;
    }
}
