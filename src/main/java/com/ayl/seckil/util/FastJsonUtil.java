package com.ayl.seckil.util;

import com.alibaba.fastjson.JSON;

/**
 * @author AYL    2018/8/25 1:30
 */
public class FastJsonUtil {
    public static Object jsongToObject(String value, Class clazz) {
        if (clazz.isInstance(Integer.class)) {
            return Integer.valueOf(value);
        } else if (clazz.isInstance(Long.class)) {
            return Long.parseLong(value);
        } else if (clazz.isInstance(Double.class)) {
            return Double.valueOf(value);
        } else if (clazz.isInstance(Float.class)) {
            return Float.valueOf(value);
        } else if (clazz.isInstance(String.class)) {
            return value;
        } else {
            return JSON.parseObject(value, clazz);
        }
    }
}
