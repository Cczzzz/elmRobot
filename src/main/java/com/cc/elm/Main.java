package com.cc.elm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;

public class Main {

    static String cookie = "perf_ssid=3j2hrecf3yo3oxk0kzbl7ldfv9ancyla_2019-04-26; ubt_ssid=c7008mifnuckmxzjduy9jig9gop90dh9_2019-04-26; cna=BiWpFAc3lG0CAd9IMDONdRiE; _uab_collina=155628794760763967308453; _utrace=fb15c1db529a41cc78ccf9263673c69d_2019-04-26; snsInfo[101204453]=%7B%22city%22%3A%22%E8%BE%BD%E9%98%B3%22%2C%22constellation%22%3A%22%22%2C%22eleme_key%22%3A%22a9baa175f432eafe7c3e664b799ff362%22%2C%22figureurl%22%3A%22http%3A%2F%2Fqzapp.qlogo.cn%2Fqzapp%2F101204453%2F380D2F0322644754A519CF60441D19FB%2F30%22%2C%22figureurl_1%22%3A%22http%3A%2F%2Fqzapp.qlogo.cn%2Fqzapp%2F101204453%2F380D2F0322644754A519CF60441D19FB%2F50%22%2C%22figureurl_2%22%3A%22http%3A%2F%2Fqzapp.qlogo.cn%2Fqzapp%2F101204453%2F380D2F0322644754A519CF60441D19FB%2F100%22%2C%22figureurl_qq%22%3A%22http%3A%2F%2Fthirdqq.qlogo.cn%2Fg%3Fb%3Doidb%26k%3DGgqWLTFoJSbsomS4IIKS2Q%26s%3D140%22%2C%22figureurl_qq_1%22%3A%22http%3A%2F%2Fthirdqq.qlogo.cn%2Fg%3Fb%3Doidb%26k%3DGgqWLTFoJSbsomS4IIKS2Q%26s%3D40%22%2C%22figureurl_qq_2%22%3A%22http%3A%2F%2Fthirdqq.qlogo.cn%2Fg%3Fb%3Doidb%26k%3DGgqWLTFoJSbsomS4IIKS2Q%26s%3D100%22%2C%22figureurl_type%22%3A%221%22%2C%22gender%22%3A%22%E7%94%B7%22%2C%22is_lost%22%3A0%2C%22is_yellow_vip%22%3A%220%22%2C%22is_yellow_year_vip%22%3A%220%22%2C%22level%22%3A%220%22%2C%22msg%22%3A%22%22%2C%22nickname%22%3A%22Cc%22%2C%22openid%22%3A%22380D2F0322644754A519CF60441D19FB%22%2C%22province%22%3A%22%E8%BE%BD%E5%AE%81%22%2C%22ret%22%3A0%2C%22vip%22%3A%220%22%2C%22year%22%3A%221996%22%2C%22yellow_vip_level%22%3A%220%22%2C%22name%22%3A%22Cc%22%2C%22avatar%22%3A%22http%3A%2F%2Fthirdqq.qlogo.cn%2Fg%3Fb%3Doidb%26k%3DGgqWLTFoJSbsomS4IIKS2Q%26s%3D40%22%7D; _umdata=GB84BCC605C958416D13167EBF0BDBE5BEEEA60; track_id=1556287998|5e1f346b76cef83723d5616c51d8f910e7db1edd3fa2108a91|48a641195b9270ae5ce5fd091a4aa873; USERID=6070003522; UTUSER=6070003522; SID=bsYIrETHQBorsJ1Ktv4H2FnRwb26nEhqpSMg; ZDS=1.0|1556287998|Qj+Gq2FrrAQeupWrsVrA0j6dgSSSb3KHLkVHv0kRWYoJTLYFbhMVvpMYtnDUjTeBiT0kIkF4i6e8sE+LIhJ1Ig==; isg=BFBQDU4aOBAU6-RqSqRdaSxxNJ5isTVhD-YRCUohGKtIhfQv4SnF8bGnWwvAVew7";
    static String req = "{\"method\":\"phone\",\"group_sn\":\"1d3f586607054874\",\"sign\":\"a9baa175f432eafe7c3e664b799ff362\",\"phone\":\"\",\"device_id\":\"\",\"hardware_id\":\"\",\"platform\":4,\"track_id\":\"1556287998|5e1f346b76cef83723d5616c51d8f910e7db1edd3fa2108a91|48a641195b9270ae5ce5fd091a4aa873\",\"weixin_avatar\":\"http://thirdqq.qlogo.cn/g?b=oidb&k=GgqWLTFoJSbsomS4IIKS2Q&s=40\",\"weixin_username\":\"Cc\",\"unionid\":\"fuck\",\"latitude\":\"\",\"longitude\":\"\"}";
    public static void main(String[] args) throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .version(HttpClient.Version.HTTP_2)
//                .uri(URI.create("https://h5.ele.me/restapi/eus/login/login_by_mobile"))
//                .POST(HttpRequest.BodyPublishers.ofString(req))
//                .setHeader("cookie","perf_ssid=wi28yruem1n267j330nz7qe6y2cudd12_2019-04-23; ubt_ssid=uf5ufk0yu0r3pi8h2a6ny7wnambu9yye_2019-04-23; _uab_collina=155602260364419590724527; cna=BiWpFAc3lG0CAd9IMDONdRiE; _utrace=de3b9c4e7a486f6f50d74068d241f091_2019-04-23; snsInfo[101204453]=%7B%22city%22%3A%22%22%2C%22constellation%22%3A%22%22%2C%22eleme_key%22%3A%225f0ba5a1dcccf3d50977b98437a29b9d%22%2C%22figureurl%22%3A%22http%3A%2F%2Fqzapp.qlogo.cn%2Fqzapp%2F101204453%2FEC7B2F7DEDA74A44DDA4F16DB31BDF83%2F30%22%2C%22figureurl_1%22%3A%22http%3A%2F%2Fqzapp.qlogo.cn%2Fqzapp%2F101204453%2FEC7B2F7DEDA74A44DDA4F16DB31BDF83%2F50%22%2C%22figureurl_2%22%3A%22http%3A%2F%2Fqzapp.qlogo.cn%2Fqzapp%2F101204453%2FEC7B2F7DEDA74A44DDA4F16DB31BDF83%2F100%22%2C%22figureurl_qq%22%3A%22http%3A%2F%2Fthirdqq.qlogo.cn%2Fg%3Fb%3Doidb%26k%3Dx428HkIfTq6NQCynUSHqQg%26s%3D140%22%2C%22figureurl_qq_1%22%3A%22http%3A%2F%2Fthirdqq.qlogo.cn%2Fg%3Fb%3Doidb%26k%3Dx428HkIfTq6NQCynUSHqQg%26s%3D40%22%2C%22figureurl_qq_2%22%3A%22http%3A%2F%2Fthirdqq.qlogo.cn%2Fg%3Fb%3Doidb%26k%3Dx428HkIfTq6NQCynUSHqQg%26s%3D100%22%2C%22figureurl_type%22%3A%221%22%2C%22gender%22%3A%22%E5%A5%B3%22%2C%22is_lost%22%3A0%2C%22is_yellow_vip%22%3A%220%22%2C%22is_yellow_year_vip%22%3A%220%22%2C%22level%22%3A%220%22%2C%22msg%22%3A%22%22%2C%22nickname%22%3A%22%E4%BD%A0%E6%98%AF%E4%B8%8D%E6%98%AF%E5%93%88%E6%89%B9%E5%95%8A%22%2C%22openid%22%3A%22EC7B2F7DEDA74A44DDA4F16DB31BDF83%22%2C%22province%22%3A%22%22%2C%22ret%22%3A0%2C%22vip%22%3A%220%22%2C%22year%22%3A%221996%22%2C%22yellow_vip_level%22%3A%220%22%2C%22name%22%3A%22%E4%BD%A0%E6%98%AF%E4%B8%8D%E6%98%AF%E5%93%88%E6%89%B9%E5%95%8A%22%2C%22avatar%22%3A%22http%3A%2F%2Fthirdqq.qlogo.cn%2Fg%3Fb%3Doidb%26k%3Dx428HkIfTq6NQCynUSHqQg%26s%3D40%22%7D; _umdata=GB84BCC605C958416D13167EBF0BDBE5BEEEA60; track_id=1556022691|fa83b1331ef7c85b56fe84b4c2a49aefda891294a9c6612783|1f64f4b766c51992ab40ada0a8873a32; USERID=16499326; UTUSER=16499326; SID=l1jPW22ab9XCTjyAXdbk4Rqjq0tciHKanq3Q; isg=BEhIKzENgNQ75-xSAlzVwWTJDLaaWa35104ZEQL5kkO23e5HuQBVifebUfMIbWTT").build();
//        HttpClient client = HttpClient.newBuilder().build();
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.statusCode());
//        System.out.println(response.body());

        //JSONObject jsonObject = JSONObject.parseObject(req);

        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(URI.create("https://h5.ele.me/restapi/marketing/promotion/weixin/380D2F0322644754A519CF60441D19FB"))
                .POST(HttpRequest.BodyPublishers.ofString(req))
                .setHeader("cookie", cookie)
//                .setHeader("Content-Type", "text/plain;charset=UTF-8")
//                .setHeader("User-Agent", "PostmanRuntime/7.11.0")

                .build();


        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());

    }
}
