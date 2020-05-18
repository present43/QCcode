package com.qingcheng.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.goods.Para;
import com.qingcheng.service.goods.ParaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/para")
public class ParaController {
    //注入远程服务
    @Reference
    ParaService paraService;

    //查询所有的方法
    @RequestMapping("/findAll")
    public List<Para> findAll() {
        return paraService.findAll();
    }

    //参数条件 分页查询
    @PostMapping("/findPage")
    PageResult<Para> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return paraService.findPage(searchMap, page, size);
    }

    //根据ID进行查询
    @GetMapping("/findById")
    public Para findById(Integer id) {
        return paraService.findByID(id);
    }

    //   参数新增方法
    @PostMapping("/add")
    public Result add(@RequestBody Para para) {
        paraService.add(para);
        return new Result();
    }

    //  参数修改方法
    @PostMapping("/update")
    public Result update(@RequestBody Para para) {
        paraService.update(para);
        return new Result();
    }


}
