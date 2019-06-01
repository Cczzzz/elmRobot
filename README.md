# elmRobot
饿了吗领最大红包机器人

## cookie
暂时使用redis存储cookie
主要需要cookie中的snsInfo 取得openid和eleme_key

## 饿了吗接口
红包的链接包含两主要信息 sn 和 theme_id

接口1：取得最大红包数 get请求 
地址 ：https://h5.ele.me/restapi/marketing/themes/{theme_id}/group_sns/{sn}
此接口不需要cookie 

接口2：领取红包 post请求 
地址：https://h5.ele.me/restapi/marketing/promotion/weixin/{openid}
请求体：
```
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
```
其中group_sn为红包链接的{sn}  sign， 为用户cookie中的eleme_key ，cookie为完整cookie， phone为领取用户的手机号
其它信息非必选

### 现在好像凉了 仅当学习用吧
    
