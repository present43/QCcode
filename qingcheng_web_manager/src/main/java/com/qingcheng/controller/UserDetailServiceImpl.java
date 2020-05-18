package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.pojo.system.Admin;
import com.qingcheng.service.system.AdminService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//封装了对用户的 信息读取
public class UserDetailServiceImpl implements UserDetailsService {

    //注入服务
    @Reference
    private AdminService adminService;


    //String s 用户在界面填写的值
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        System.out.println("证明经过了这里");

        Map map = new HashMap<>();
        //传入登录名 s
        map.put("loginName", loginName);
        // 唯一
        map.put("status", "1");
        List<Admin> list = adminService.findList(map);
        //如果 没有查到
        if (list.size() == 0) {
            return null;
        }

        // 当前用户所拥有的角色  构建权限列表
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        //查询出数据库里 用户对应的角色
        List<String> resKeyList = adminService.Authority(loginName);
        //添加角色  正常情况下是从数据库中提取用户的角色列表
        for (String resKey: resKeyList) {

           grantedAuthorities.add(new SimpleGrantedAuthority(resKey));
            System.out.println(resKey);
        }

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        // 包含三个参数 用户名 密码 权限集合
        return new User(loginName, list.get(0).getPassword(), grantedAuthorities);
    }
}
