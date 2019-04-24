package com.cc.elm.common.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cc.elm.common.ex.MyException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultObj {

    // ----------------------------------------异常码(http状态码)--------------------------------------------------
    // 正常返回
    public static final int SUCCESS = 200;

    // 数据没有发生变化(一般用于更新操作时，没有发生变化)
    public static final int NOT_MODIFIED = 304;

    // 客户端它发送了一个错误的请求(一般是参数不合法，可以在校验异常中使用)
    public static final int BAD_REQUEST = 400;

    // 没有认证(一般是没有登录)
    public static final int UNAUTHORIZED = 401;

    // 请求被服务器拒绝了(一般是认证通过了,但是没有权限访问该资源)
    public static final int FORBIDDEN = 403;

    // 服务器无法找到所请求的URL(一般在Controller中使用)
    public static final int NOT_FOUND = 404;

    // 发起的请求中带有所请求的URL不支持的方法时(如果该方法仅支持POST请求，用GET请求则报错)
    public static final int METHOD_NOT_ALLOWED = 405;

    // 请求超时(客户端完成请求所花的时间太长)
    public static final int REQUEST_TIMEOUT = 408;

    // 服务器异常(一般数处理逻辑出现异常,可以在service中使用)
    public static final int ERROR = 500;

    // ----------------------------------------返回信息-------------------------------------

    // 返回状态信息,默认200
    private int code = 200;

    // 返回的数据
    private Object data = null;

    // 返回的信息
    private String msg = "";


    public ResultObj(Object data) {
        this(SUCCESS, data, "");
    }

    public ResultObj(Object data, String msg) {
        this(SUCCESS, data, msg);
    }

    public ResultObj(int code, Object data, String msg) {
        if(data == null){
            data = new Object();
        }
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 处理异常信息
     *
     * @param ex
     * @return
     */
    public static String getStackMsg(MyException ex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ERROR);
        jsonObject.put("errorCode", ex.getErrorCode());
        jsonObject.put("msg", ex.getMyMessage());
        return jsonObject.toJSONString();
    }
    /**
     * 将数据添加到data
     *
     * @param key
     * @param value
     * @return
     */
    public ResultObj setData(String key, Object value) {
        // 如果data不是JSONObject对象，则将data转化为JSONObject对象
        if (!(this.data instanceof JSONObject)) {
            this.data = JSONObject.parseObject(JSON.toJSONString(this.data));
        }
        ((JSONObject) this.data).put(key, value);
        return this;
    }

}
