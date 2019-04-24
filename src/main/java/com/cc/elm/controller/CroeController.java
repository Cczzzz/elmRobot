package com.cc.elm.controller;

import com.cc.elm.common.controller.BaseController;
import com.cc.elm.common.bean.ResultObj;
import com.cc.elm.entity.ElmReq;
import com.cc.elm.service.CroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController("/croe")
public class CroeController extends BaseController {

    @Autowired
    CroeService service;

    @PostMapping()
    public ResultObj getRedPacket(String url) throws IOException, InterruptedException {
        return succeed(service.getRedPacket(url));
    }


}
