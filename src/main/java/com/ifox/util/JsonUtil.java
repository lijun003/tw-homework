package com.ifox.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
    public static <T> T getBean(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> List<T> getBeans(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
