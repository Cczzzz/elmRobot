package com.cc.elm.common.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by chenchang on 2018/1/22.
 */
@Target({ElementType.FIELD})//注解类型
@Retention(RetentionPolicy.RUNTIME)//在运行时有效
public @interface RedisColumn {
}
