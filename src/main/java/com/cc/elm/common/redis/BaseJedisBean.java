package com.cc.elm.common.redis;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by chenchang on 2018/1/22.
 */
public abstract class BaseJedisBean<T extends BaseJedisBean> {

    /**
     * 取得存redis的key  保证唯一性
     *
     * @return
     */
    public abstract String getRedisKey();

    public abstract String getRedisKey(String id);


    public String saveToRedis() throws IllegalAccessException {
        String key = getRedisKey();
        Map<String, String> map = toMap();
        RedisUtil.hmset(key, map);
        return key;
    }


    /**
     * 对象转map
     *
     * @return
     * @throws IllegalAccessException
     */
    private Map<String, String> toMap() throws IllegalAccessException {
        HashMap<String, String> map = new HashMap<>();
        Class clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            RedisColumn anno = field.getAnnotation(RedisColumn.class);
            Object value = field.get(this);
            if (anno != null) {
                if (value == null) {
                    value = "";
                }
                map.put(field.getName(), value == null ? null : value.toString());
            }
        }
        return map;
    }

    public static <T extends BaseJedisBean> T getByRedis(String key, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        Map e = RedisUtil.hgetAll(key);
        if (e != null && e.size() != 0) {
            T t = clazz.newInstance();
            loadFromMap(e, t);
            return t;
        } else {
            return null;
        }
    }

    private static <T> void loadFromMap(Map<String, String> fieldValueMap, T t) {
        try {
            Iterator e = fieldValueMap.keySet().iterator();

            while (e.hasNext()) {
                String key = (String) e.next();
                Field f = null;
                try {
                    f = t.getClass().getDeclaredField(key);
                } catch (Exception var6) {
                    continue;
                }
                f.setAccessible(true);
                String value = (String) fieldValueMap.get(key);
                if (f.getType() == String.class) {
                    f.set(t, value);
                } else if (value != null && !value.equals("")) {
                    if (f.getType() == Integer.class) {
                        f.set(t, Integer.valueOf(Integer.parseInt(value)));
                    } else if (f.getType() == Double.class) {
                        f.set(t, Double.valueOf(Double.parseDouble(value)));
                    } else if (f.getType() == Long.class) {
                        f.set(t, Long.valueOf(Long.parseLong(value)));
                    }
                }
            }

        } catch (Exception var7) {
            var7.printStackTrace();
        }
    }
}
