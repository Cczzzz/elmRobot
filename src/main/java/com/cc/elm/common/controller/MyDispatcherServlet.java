package com.cc.elm.common.controller;

import com.cc.elm.common.ex.MyException;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyDispatcherServlet extends DispatcherServlet {

    private static final long serialVersionUID = 1L;

    /**
     * 重写springmvc处理异常的方法,直接项目自定义的异常
     */
    @Override
    protected ModelAndView processHandlerException(HttpServletRequest request, HttpServletResponse response,
                                                   Object handler, Exception ex) throws Exception {
        if (ex instanceof MyException) {
            // 如果是异步请求
            MyException.outputEx((MyException) ex,response);
            return null;
        }
        return super.processHandlerException(request, response, handler, ex);
    }

}