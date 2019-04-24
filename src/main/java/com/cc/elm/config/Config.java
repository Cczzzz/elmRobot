package com.cc.elm.config;

import com.cc.elm.common.controller.MyDispatcherServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public MyDispatcherServlet dispatcherServlet(){
        return new MyDispatcherServlet();
    }
}
