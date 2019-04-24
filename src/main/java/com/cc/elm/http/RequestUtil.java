package com.cc.elm.http;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class RequestUtil {

    public static HttpResonse get(String url, String cookie) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(URI.create(url))
                .GET()
                .setHeader("cookie", Optional.ofNullable(cookie).orElse(""))
                .setHeader("Content-Type", "text/plain;charset=UTF-8")
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new HttpResonse(response.body(), response.statusCode());
    }

    public static HttpResonse Post(String url, Object requestPayload, String cookie) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(JSON.toJSONString(requestPayload)))
                .setHeader("cookie", cookie)
                .setHeader("Content-Type", "text/plain;charset=UTF-8")
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new HttpResonse(response.body(), response.statusCode());
    }

}
