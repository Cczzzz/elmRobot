package com.cc.elm.service;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ResolveUrl {

    static Map<String, String> resolve(String url) {
        url = url.replaceAll("amp;", "");
        Map<String, String> result = Stream.of(url.split("&"))
                .map(u -> u.split("="))
                .filter(u -> u.length == 2)
                .collect(Collectors.toMap(k -> k[0], v -> v[1]));
        return result;
    }

    static void main(String[] args) {
        Map<String, String> resolve = resolve("https://h5.ele.me/hongbao/#hardware_id=&amp;is_lucky_group=True&amp;lucky_number=0&amp;track_id=&amp;platform=0&amp;sn=1d3ee7c62107c4ac&amp;theme_id=4099&amp;device_id=&amp;refer_user_id=13870696");
        System.currentTimeMillis();
    }
}
