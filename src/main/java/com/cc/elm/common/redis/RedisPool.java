package com.cc.elm.common.redis;

import com.cc.elm.common.ex.MyException;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * Created by chenchang on 2018/1/22.
 */
public class RedisPool {

    private static JedisPool jedisPool;//非切片连接池

    private static String path = "redis.properties";

    /**
     * 初始化非切片池
     */
    private static void initialPool(String host, Integer post, Integer timeout, String passWord, Integer dataBase) {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(200);

        //testWhileIdle：如果为true，表示有一个idle object evitor线程对idle object进行扫描，如果validate失败，此object会被从pool中drop掉；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义；
        config.setTestWhileIdle(true);

        //minEvictableIdleTimeMillis：表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义；
        config.setMinEvictableIdleTimeMillis(60000);

        //timeBetweenEvictionRunsMillis：表示idle object evitor两次扫描之间要sleep的毫秒数；
        config.setTimeBetweenEvictionRunsMillis(30000);

        //numTestsPerEvictionRun：表示idle object evitor每次扫描的最多的对象数；
        config.setNumTestsPerEvictionRun(-1);

        jedisPool = new JedisPool(config, host, post, timeout, passWord, dataBase);
    }

    //初始化连接池
    static {
        Properties propertie;//装载配置文件
        try {
            propertie = PropertiesLoaderUtils.loadAllProperties(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException("redis配置文件未找到");
        }
        String host = propertie.getProperty("host");
        Integer port = Integer.valueOf(propertie.getProperty("port"));
        Integer timeout =Integer.valueOf(propertie.getProperty("timeout"));
        String password = propertie.getProperty("password");
        if(password.equals("")){
            password = null;
        }
        Integer database =Integer.valueOf(propertie.getProperty("database"));

        initialPool(host,port,timeout,password,database);
    }

    public static synchronized Jedis getResource(){
        return jedisPool.getResource();
    }
    public static  void returnResource(Jedis jedis) {
        jedisPool.returnResourceObject(jedis);
    }


    public static void main(String[] args) {
        Properties propertie = null;//装载配置文件
        try {
            propertie = PropertiesLoaderUtils.loadAllProperties(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException("redis配置文件未找到");
        }
        String host = propertie.getProperty("host");
        Integer port = Integer.valueOf(propertie.getProperty("port"));
        Integer timeout =Integer.valueOf(propertie.getProperty("timeout"));
        String password = propertie.getProperty("password");
        if(password.equals("")){
            password = null;
        }
        Integer database =Integer.valueOf(propertie.getProperty("database"));

        initialPool(host,port,timeout,password,database);

        Jedis jedis = jedisPool.getResource();
        System.out.println("判断key999键是否存在："+jedis.exists("key999"));
        System.out.println("新增key001,value001键值对："+jedis.set("key001", "value001"));
        System.out.println("判断key001是否存在："+jedis.exists("key001"));
        System.out.println("新增key002,value002键值对："+jedis.set("key002", "value002"));
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }

        // 设置 key001的过期时间
        System.out.println("设置 key001的过期时间为5秒:"+jedis.expire("key001", 5));
        try{
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("判断key001是否存在："+jedis.exists("key001"));
        System.out.println("key001的值："+jedis.get("key001"));

        // 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
        System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
        // 移除某个key的生存时间
        System.out.println("移除key001的生存时间："+jedis.persist("key001"));
        System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));

        // 查看key所储存的值的类型
        System.out.println("查看key所储存的值的类型："+jedis.type("key001"));
    }

}
