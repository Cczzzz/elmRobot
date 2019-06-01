package com.cc.elm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cc.elm.common.ex.MyException;
import com.cc.elm.common.redis.BaseJedisBean;
import com.cc.elm.common.redis.BaseJedisList;
import com.cc.elm.entity.ElmReq;
import com.cc.elm.entity.ElmUrl;
import com.cc.elm.entity.RCookie;
import com.cc.elm.entity.RequestPayload;
import com.cc.elm.http.HttpResonse;
import com.cc.elm.http.RequestUtil;
import com.cc.elm.service.CroeService;
import com.cc.elm.service.ResolveUrl;
import lombok.extern.log4j.Log4j;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

@Service
public class CroeServiceImpl implements CroeService {

    final static Logger LOGGER = LoggerFactory.getLogger(CroeServiceImpl.class);

    public final String themeId = "theme_id";
    public final String snId = "sn";
    public final String luckyNumber = "lucky_number";

    @Override
    public Integer getLuckyNumber(String themeId, String snId) throws IOException, InterruptedException {
        HttpResonse resonse = RequestUtil.get(ElmUrl.baseUrl + ElmUrl.luckyNumber + themeId + ElmUrl.luckyNumber_groupSns + snId, null);
        if (resonse.getStatusCode() != 200) {
            throw new MyException("获取最大红包是第几个失败");
        }
        Object number = JSON.parseObject(resonse.getBody()).get(luckyNumber);
        return (Integer) number;
    }

    @Override
    public ElmReq getRedPacket(String url) throws IOException, InterruptedException, IllegalAccessException, InstantiationException {
        Map<String, String> urlParameter = ResolveUrl.resolve(url);
        //获取luckyNumber
        int luckyNumber = getLuckyNumber(urlParameter.get(themeId), urlParameter.get(snId));
        ElmReq elmReq = new ElmReq();
        elmReq.setLuckyNumber(luckyNumber);
        int now = 0;//当前领到第几个
        long llenn = BaseJedisList.llenn(RCookie.class);//redis中队列数
        long index = 0;//循环队列index
        //循环从redis中取cookie 直到领取到luckyNumber -1 或者队列取完
        while (now < luckyNumber - 1 && index < llenn) {
            RCookie rCookie = BaseJedisList.lindex(index, RCookie.class);
            RequestPayload requestPay = transform(rCookie);
            requestPay.setGroup_sn(urlParameter.get(snId));
            Integer send = send(requestPay);
            if (send != null) {
                now = send;
            }
            index++;
        }
        elmReq.setAtNumber(now);
        return elmReq;
    }

    //发送领红包请求
    public Integer send(RequestPayload requestPayload) throws IOException, InterruptedException {
        HttpResonse httpResonse = RequestUtil.Post(ElmUrl.haobaoUrl + requestPayload.getOpenId(), requestPayload, requestPayload.getCookie());
        if (httpResonse.getStatusCode() == 200) {
            JSONArray promotion_items = JSONObject.parseObject(httpResonse.getBody()).getJSONArray("promotion_records");
            return promotion_items.size();
        } else {
            LOGGER.warn("领取失败=>" + JSON.toJSONString(requestPayload)+"\n接口信息"+JSON.toJSONString(httpResonse.getBody()));
            return null;
        }
    }


    @Override
    public void contribute(Long phone, String cookie) throws IllegalAccessException, InstantiationException {
        RCookie cookieBean = new RCookie();
        RCookie rCookie = BaseJedisBean.getByRedis(cookieBean.getRedisKey(String.valueOf(phone)), RCookie.class);
        if (rCookie != null) {
            throw new MyException("此cookie重复");
        }
        //截取snsInfo
        int start = cookie.indexOf("=", cookie.indexOf("snsInfo"));
        int end = cookie.indexOf(";", start);
        String snsInfo = cookie.substring(start + "=".length(), end);
        //url解码
        JSONObject jsonObject = JSONObject.parseObject(URLDecoder.decode(snsInfo, StandardCharsets.UTF_8));

        cookieBean.setCookie(cookie);
        cookieBean.setPhone(phone);
        cookieBean.setOpenId(jsonObject.getString("openid"));
        cookieBean.setSign(jsonObject.getString("eleme_key"));
        //存入redis
        BaseJedisList.rpush(Collections.singletonList(cookieBean));
    }

    public RequestPayload transform(RCookie cookie) {
        RequestPayload requestPayload = new RequestPayload();
        requestPayload.setOpenId(cookie.getOpenId());
        requestPayload.setPhone(cookie.getPhone());
        requestPayload.setSign(cookie.getSign());
        requestPayload.setCookie(cookie.getCookie());
        return requestPayload;
    }


}
