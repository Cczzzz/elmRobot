package com.cc.elm.common.redis;

import com.cc.elm.common.ex.MyException;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseJedisList<T extends BaseJedisList> extends BaseJedisBean<T> {

    public static <T extends BaseJedisList> long llenn(Class<T> tClass) {
        String listKey = getListKeyByClass(tClass);
        return RedisUtil.llen(listKey);
    }
    public static <T extends BaseJedisList> T lindex(long index, Class<T> tClass) throws InstantiationException, IllegalAccessException {
        String listKey = getListKeyByClass(tClass);
        String lindex = RedisUtil.lindex(listKey, index);
        return  getByRedis(lindex,tClass);
    }


    public static <T extends BaseJedisList> List<T> lrange(long start, long stop, Class<T> tClass) throws InstantiationException, IllegalAccessException {
        String listKey = getListKeyByClass(tClass);
        List<String> lrangeKeys = RedisUtil.lrange(listKey, start, stop);
        List<T> list = new ArrayList<>(lrangeKeys.size());
        for (String key : lrangeKeys) {
            T t = (T) getByRedis(key, tClass);
            list.add(t);
        }
        return list;
    }

    public static <T extends BaseJedisList> Long rpush(List<T> baseJedisLists) throws IllegalAccessException {
        if (baseJedisLists == null || baseJedisLists.size() == 0) {
            throw new MyException("该集合为空");
        }
        String listKey = baseJedisLists.get(0).getListKey();
        List<String> list = new ArrayList<>(baseJedisLists.size());
        for (BaseJedisList jedisbean : baseJedisLists) {
            list.add(jedisbean.saveToRedis());
        }
        String[] strings = new String[list.size()];
        list.toArray(strings);
        return RedisUtil.rpush(listKey, strings);
    }
//    public static <T extends BaseJedisList> Long lrange(Integer index) throws IllegalAccessException {
//        if (baseJedisLists == null || baseJedisLists.size() == 0) {
//            throw new MyException("该集合为空");
//        }
//        String listKey = baseJedisLists.get(0).getListKey();
//        List<String> list = new ArrayList<>(baseJedisLists.size());
//        for (BaseJedisList jedisbean : baseJedisLists) {
//            list.add(jedisbean.saveToRedis());
//        }
//        String[] strings = new String[list.size()];
//        list.toArray(strings);
//        return RedisUtil.rpush(listKey, strings);
//    }


    public String getListKey() {
        Class<T> tClass = (Class<T>) this.getClass();
        return getListKey(tClass);
    }

    public static <T extends BaseJedisList> String getListKey(Class<T> tClass) {
        Annotation annotation = tClass.getAnnotation(RedisList.class);
        if (annotation == null) {
            throw new MyException("该类没有@RedisList注解");
        }
        RedisList redisList = (RedisList) annotation;
        String folderName = redisList.folderName();
        String listKey = redisList.listKey();
        return folderName + ":" + listKey;
    }

    public static <T extends BaseJedisList> String getListKeyByClass(Class<T> tClass) {
        return getListKey(tClass);
    }

}
