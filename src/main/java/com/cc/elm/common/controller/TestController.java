package com.cc.elm.common.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by chenchang on 2018/1/19.
 */
@RestController
@RequestMapping("/test/")
public class TestController {

    @GetMapping("holle")
    public Object test2(String name){
        return  "holle!  "+name;
    }

    @GetMapping("holle2")
    public Object holle2(){
        return  "holle! word";
    }

    @PostMapping("file")
    public void test(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String base = request.getServletContext().getRealPath("/");
        System.out.println(file.getName());
        String name = file.getOriginalFilename();
        File file1 = new File(base + File.separator + "down" + File.separator + name);
        if (!file1.exists()) {
            file1.createNewFile();
        }
    }


}
