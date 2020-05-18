package com.qingcheng.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.goods.Template;
import com.qingcheng.service.goods.TemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/template")
public class TemplateController {

    //注入服务 因为用的是zookeeper 所以注入远程服务
    @Reference
    private TemplateService templateService;

    //查询所有的方法
    @RequestMapping("/findAll")
    public List<Template> findAll() {
        return templateService.findAll();
    }

    //分页查询
    @GetMapping("/findPage")
    public PageResult<Template> findPage(int page, int size) {
        return templateService.findPage(page, size);
    }

    //条件分页查询  可以看成上一个方法的增强版  方法的重载同时选用post请求
    @PostMapping("/findPage")
    public PageResult<Template> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return templateService.findPage(searchMap, page, size);
    }

    //模板新增方法  可能有bug只加的进去名称 也可能是数据库问题
    @PostMapping("/add")
    public Result add(@RequestBody Template template) {
        templateService.add(template);
        return new Result();
    }

    //模板修改办法
    @PostMapping("/update")
    public Result update(@RequestBody Template template) {
        templateService.update(template);
        return new Result();
    }

    //根据主键查询方法


    //根据ID删除方法
    @GetMapping("/delete")
    public Result delete(Integer id){

        return new Result();
    }
}
