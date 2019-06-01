package com.cc.elm.entity;

import com.cc.elm.common.redis.BaseJedisList;
import com.cc.elm.common.redis.RedisColumn;
import com.cc.elm.common.redis.RedisList;
import lombok.Data;

@Data
@RedisList
public class RCookie extends BaseJedisList<RCookie> {
    /**
     * 存rdeis 文件夹名
     */
    private static final String folderName = "cookie:";

    //cookie中open_id
    @RedisColumn
    private String openId;
    //cookie中eleme_key
    @RedisColumn
    private String sign;
    //电话号码
    @RedisColumn
    private Long phone;
    @RedisColumn
    private String cookie;

    private String device_id = "";
    private String hardware_id = "";
    private Double latitude = 111D;
    private Double longitude = 111D;
    private String method = "phone";
    private Integer platform = 4;
    private String track_id = "undefined";
    private String unionid = "fuck";
    private String weixin_avatar = "";
    private String weixin_username = "";


    @Override
    public String getRedisKey() {
        return folderName + phone;
    }

    @Override
    public String getRedisKey(String id) {
        return folderName + id;
    }

}
