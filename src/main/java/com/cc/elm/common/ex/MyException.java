package com.cc.elm.common.ex;



import com.cc.elm.common.bean.ResultObj;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyException extends RuntimeException {

    private String MyMessage;
    private Integer errorCode;

    public MyException() {
        super();
    }

    public MyException(String myMessage) {
        this(myMessage, null);
    }


    public MyException(String myMessage, Integer errorCode) {
        super(myMessage);
        MyMessage = myMessage;
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMyMessage() {

        return MyMessage;
    }

    /**
     * 异常处理方法 响应输出异常信息
     *
     * @param ex
     * @param servletResponse
     * @throws IOException
     */
    public static void outputEx(MyException ex, ServletResponse servletResponse) throws IOException {
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = servletResponse.getWriter();
        writer.write(ResultObj.getStackMsg((MyException) ex));
        writer.flush();
    }
}
