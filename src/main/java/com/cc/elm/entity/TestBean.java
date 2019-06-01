package com.cc.elm.entity;

import com.cc.elm.common.redis.BaseJedisList;
import com.cc.elm.common.redis.RedisColumn;
import com.cc.elm.common.redis.RedisList;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@RedisList
public class TestBean extends BaseJedisList<TestBean> {
    @RedisColumn
    String id;
    @RedisColumn
    String name;

    @Override
    public String getRedisKey() {
        return id;
    }

    @Override
    public String getRedisKey(String id) {
        return id;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        TestBean testBean = new TestBean();
        testBean.setId("aaaa");
        testBean.setName("1111");

        TestBean testBean2 = new TestBean();
        testBean2.setId("bbbb");
        testBean2.setName("2222");
        List<TestBean> testBeans = Arrays.asList(testBean, testBean2);
        BaseJedisList.rpush(testBeans);
        List<TestBean> lrange = BaseJedisList.lrange(0, 2,TestBean.class);
        System.out.println(lrange);
    }

}
