package com.qingcheng.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.goods.Category;
import com.qingcheng.service.goods.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    //注入远程服务
    @Reference
    private CategoryService categoryService;

    //查询所有
    @GetMapping("/findAll")
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    //分页查询
    @GetMapping("/findPage")
    public PageResult<Category> findPage(int page, int size){
        return categoryService.findPage(page, size);
    }

    //条件查询
    @PostMapping("/findList")
    public List<Category> findList(@RequestBody Map<String,Object> searchMap){
        return categoryService.findList(searchMap);
    }

    //分页条件查询
    @PostMapping("/findPage")
    public PageResult<Category> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  categoryService.findPage(searchMap,page,size);
    }

    //主键查询
    @GetMapping("/findById")
    public Category findById(Integer id){
        return categoryService.findById(id);
    }

    //添加
    @PostMapping("/add")
    public Result add(@RequestBody Category category){
        categoryService.add(category);
        return new Result();
    }

    //更新修改
    @PostMapping("/update")
    public Result update(@RequestBody Category category){
        categoryService.update(category);
        return new Result();
    }

    //删除
    @GetMapping("/delete")
    public Result delete(Integer id){
        categoryService.delete(id);
        return new Result();
    }

}
