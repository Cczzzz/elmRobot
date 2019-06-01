package com.cc.elm.common.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})//注解类型
@Retention(RetentionPolicy.RUNTIME)//在运行时有效
public @interface RedisList {

    String listKey() default "";

    String folderName() default "list";

}
