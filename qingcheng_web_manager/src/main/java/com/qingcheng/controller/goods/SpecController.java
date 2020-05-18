package com.qingcheng.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.goods.Spec;
import com.qingcheng.service.goods.SpecService;
import org.omg.CORBA.Request;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spec")
public class SpecController {
    //注入远程服务
    @Reference
    SpecService specService;

    //查询所有的方法
    @RequestMapping("/findAll")
    public List<Spec> findAll() {
        return specService.findAll();
    }

    //参数条件 分页查询
    @PostMapping("/findPage")
    PageResult<Spec> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return specService.findPage(searchMap, page, size);
    }

    //根据ID进行查询
    @GetMapping("/findById")
    public Spec findById(Integer id) {
        return specService.findByID(id);
    }

    //   参数新增方法
    @PostMapping("/add")
    public Result add(@RequestBody Spec spec) {
        specService.add(spec);
        return new Result();
    }

    //  参数修改方法
    @PostMapping("/update")
    public Result update(@RequestBody Spec spec) {
        specService.update(spec);
        return new Result();
    }

    //删除方法
    @GetMapping("/delete")
    public Result delete(Integer id) {
        specService.delete(id);
        return new Result();
    }

}
