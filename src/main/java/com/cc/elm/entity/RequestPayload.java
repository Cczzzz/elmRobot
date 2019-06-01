package com.cc.elm.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPayload {
    private String device_id = "";
    //红包标识
    private String group_sn;
    private String hardware_id = "";
    private Double latitude = 111D;
    private Double longitude = 111D;
    private String method = "phone";
    //电话号码
    private Long phone ;
    private Integer platform = 4;
    //cookie中eleme_key
    private String sign;
    private String track_id = "undefined";
    private String unionid = "fuck";
    private String weixin_avatar = "http://thirdqq.qlogo.cn/g?b=oidb&k=GgqWLTFoJSbsomS4IIKS2Q&s=40";
    private String weixin_username = "";
    private String openId;
    private String cookie;

}
