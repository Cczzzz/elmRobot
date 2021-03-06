package com.cc.elm.service;

import com.cc.elm.entity.ElmReq;

import java.io.IOException;

public interface CroeService {
    /**
     * 判断最佳红包是第几个
     *
     * @param themeId
     * @param snId
     * @return
     */
    Integer getLuckyNumber(String themeId, String snId) throws IOException, InterruptedException;

    /**
     * 领取红包
     *
     * @param url
     */
    ElmReq getRedPacket(String url) throws IOException, InterruptedException, IllegalAccessException, InstantiationException;

    /**
     * 贡献cookie
     * @param phone 电话
     * @param cookie cookie
     */
    void contribute(Long phone, String cookie) throws IllegalAccessException, InstantiationException;
}
