package com.qingcheng.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.system.Admin;
import com.qingcheng.pojo.system.vo.AdminVO;
import com.qingcheng.service.system.AdminService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    //注入远程服务
    @Reference
    private AdminService adminService;

    //查询所有管理员
    @GetMapping("/findAll")
    public List<Admin> findAll() {
        return adminService.findAll();
    }

    //条件查询
    @PostMapping("/findList")
    public List<Admin> findList(@RequestBody Map<String, Object> searchMap) {
        return adminService.findList(searchMap);
    }

    //主键查询
    @GetMapping("/findById")
    public Admin findById(Integer id) {
        return adminService.findById(id);
    }

    //添加 管理员
    @PostMapping("/add")
    public Result add(@RequestBody Admin admin) {
        adminService.add(admin);
        return new Result();
    }

    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return new Result();
    }

    //删除
    @GetMapping("/delete")
    public Result delete(Integer id) {
        adminService.delete(id);
        return new Result();
    }

    //登录名查权限列表

}
