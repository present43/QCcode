package com.qingcheng.controller;

import com.qingcheng.pojo.system.Admin;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    //返回当前登录账号的名
    @GetMapping("/name")
    public Map showName() {
        //获得上下文
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        return map;
    }
}
