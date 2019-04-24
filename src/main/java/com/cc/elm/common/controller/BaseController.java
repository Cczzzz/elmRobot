package com.cc.elm.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.cc.elm.common.bean.ResultObj;
import com.cc.elm.common.ex.ErrorCode;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by chenchang on 2018/1/18.
 */
@ResponseBody
public class BaseController {

    public ResultObj succeed(Object data) {
        return  new ResultObj(ErrorCode.OK.getCode(), data, "");
    }

    public ResultObj setData(String key, Object data) {
        return succeed().setData(key,data);
    }

    public ResultObj succeed() {
        return new ResultObj(ErrorCode.OK.getCode(), new JSONObject(), "");
    }


}