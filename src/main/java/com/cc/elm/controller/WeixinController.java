package com.cc.elm.controller;

import com.cc.elm.common.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeixinController extends BaseController {

    @PostMapping
    public String test(String xml) {
        return xml;
    }
}
