package com.cc.elm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cc.elm.common.ex.MyException;
import com.cc.elm.entity.ElmReq;
import com.cc.elm.entity.ElmUrl;
import com.cc.elm.entity.RequestPayload;
import com.cc.elm.http.HttpResonse;
import com.cc.elm.http.RequestUtil;
import com.cc.elm.service.CroeService;
import com.cc.elm.service.ResolveUrl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class CroeServiceImpl implements CroeService {

    public final String themeId = "theme_id";
    public final String snId = "sn";
    public final String luckyNumber = "lucky_number";
    static String cookie = "perf_ssid=oobk097a7w1j5ipnr0p0nothhwryap88_2019-04-23; ubt_ssid=pmoyc4l2qv2xx4x3phs6b5m7syvbxkpg_2019-04-23; cna=BiWpFAc3lG0CAd9IMDONdRiE; _uab_collina=155600494151129512994855; _utrace=26d6d5ae30af512b0319dbb8a914b8cd_2019-04-23; snsInfo[101204453]=%7B%22city%22%3A%22%22%2C%22constellation%22%3A%22%22%2C%22eleme_key%22%3A%225f0ba5a1dcccf3d50977b98437a29b9d%22%2C%22figureurl%22%3A%22http%3A%2F%2Fqzapp.qlogo.cn%2Fqzapp%2F101204453%2FEC7B2F7DEDA74A44DDA4F16DB31BDF83%2F30%22%2C%22figureurl_1%22%3A%22http%3A%2F%2Fqzapp.qlogo.cn%2Fqzapp%2F101204453%2FEC7B2F7DEDA74A44DDA4F16DB31BDF83%2F50%22%2C%22figureurl_2%22%3A%22http%3A%2F%2Fqzapp.qlogo.cn%2Fqzapp%2F101204453%2FEC7B2F7DEDA74A44DDA4F16DB31BDF83%2F100%22%2C%22figureurl_qq%22%3A%22http%3A%2F%2Fthirdqq.qlogo.cn%2Fg%3Fb%3Doidb%26k%3Dx428HkIfTq6NQCynUSHqQg%26s%3D140%22%2C%22figureurl_qq_1%22%3A%22http%3A%2F%2Fthirdqq.qlogo.cn%2Fg%3Fb%3Doidb%26k%3Dx428HkIfTq6NQCynUSHqQg%26s%3D40%22%2C%22figureurl_qq_2%22%3A%22http%3A%2F%2Fthirdqq.qlogo.cn%2Fg%3Fb%3Doidb%26k%3Dx428HkIfTq6NQCynUSHqQg%26s%3D100%22%2C%22figureurl_type%22%3A%221%22%2C%22gender%22%3A%22%E5%A5%B3%22%2C%22is_lost%22%3A0%2C%22is_yellow_vip%22%3A%220%22%2C%22is_yellow_year_vip%22%3A%220%22%2C%22level%22%3A%220%22%2C%22msg%22%3A%22%22%2C%22nickname%22%3A%22%E4%BD%A0%E6%98%AF%E4%B8%8D%E6%98%AF%E5%93%88%E6%89%B9%E5%95%8A%22%2C%22openid%22%3A%22EC7B2F7DEDA74A44DDA4F16DB31BDF83%22%2C%22province%22%3A%22%22%2C%22ret%22%3A0%2C%22vip%22%3A%220%22%2C%22year%22%3A%221996%22%2C%22yellow_vip_level%22%3A%220%22%2C%22name%22%3A%22%E4%BD%A0%E6%98%AF%E4%B8%8D%E6%98%AF%E5%93%88%E6%89%B9%E5%95%8A%22%2C%22avatar%22%3A%22http%3A%2F%2Fthirdqq.qlogo.cn%2Fg%3Fb%3Doidb%26k%3Dx428HkIfTq6NQCynUSHqQg%26s%3D40%22%7D; _umdata=G45529728B96A30F8F03319B19B97ED81B3856B; track_id=1556005120|bbb6663a8752de6ac07ebb66b0262853afbb4dd658b80f2eba|cd98e68f55e61767e138d5f7c48707e1; USERID=16499326; UTUSER=16499326; SID=i42VRuBonE2Hrls9Qk1HvKuGuAfiT9Q8yveg; isg=BDg4WxyBkCVuCvyikiylkdR5HOYKCZ0ppx7pwXKpgHMmjdx3C7GVuWbrQcOY3VQD";

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
    public ElmReq getRedPacket(String url) throws IOException, InterruptedException {
        Map<String, String> urlParameter = ResolveUrl.resolve(url);
        int luckyNumber = getLuckyNumber(urlParameter.get(themeId), urlParameter.get(snId));
        //todo 取得cookie
        RequestPayload requestPayload = new RequestPayload();
        requestPayload.setGroup_sn(urlParameter.get(snId));
        requestPayload.setPhone(15641680695D);
        requestPayload.setSign("5f0ba5a1dcccf3d50977b98437a29b9d");
        requestPayload.setOpenId("EC7B2F7DEDA74A44DDA4F16DB31BDF83");
        HttpResonse httpResonse = RequestUtil.Post(ElmUrl.haobaoUrl + requestPayload.getOpenId(), requestPayload, cookie);
        ElmReq elmReq = new ElmReq();
        elmReq.setLuckyNumber(luckyNumber);
        if (httpResonse.getStatusCode() == 200) {
            JSONArray promotion_items = JSONObject.parseObject(httpResonse.getBody()).getJSONArray("promotion_records");
            elmReq.setAtNumber(promotion_items.size());
        } else {
            throw new MyException("领取失败");
        }
        return elmReq;
    }
}
