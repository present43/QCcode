package com.qingcheng.controller.goods;

//控制层 用来调用service层的 方法

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.PageResult;
import com.qingcheng.entity.Result;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*@RestController注解相当于 @ResponseBody ＋ @Controller合在一起的作用
 1.使用@Controller 注解，
   在对应的方法上，视图解析器可以解析return 的jsp,html页面，并且跳转到相应页面
   若返回json等内容到页面，则需要加@ResponseBody注解

   2.@RestController注解
    相当于@Controller+@ResponseBody两个注解的结合，返回json数据不需要在方法前面加@ResponseBody注解了，
    但使用@RestController这个注解，就不能返回jsp,html页面，视图解析器无法解析jsp,html页面
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    //用 @Reference 注解来注入远程的业务接口
    @Reference
    private BrandService brandService;

    //    查询所有品牌
    @RequestMapping("/findAll")
    public List<Brand> findAll() {
        return brandService.findAll();

    }

    //    分页功能
    @GetMapping("/findPage")
    public PageResult<Brand> findPage(int page, int size) {
        return brandService.findPage(page, size);
    }

    //   条件查询
    @PostMapping("/findList")
    public List<Brand> findList(@RequestBody Map<String,Object> searchMap) throws ClassNotFoundException {
        return brandService.findList(searchMap);
    }

    //    分页条件查询
    @PostMapping("/findPage")
    public PageResult<Brand> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) throws ClassNotFoundException {
        return brandService.findPage(searchMap, page, size);
    }

    //    品牌主键查询
    @GetMapping("/findById")
    public Brand findById(Integer id) {
        return brandService.findById(id);
    }

    //   品牌新增方法
    @PreAuthorize("hasAuthority('brand')")
    @PostMapping("/add")
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result();
    }

    //  品牌修改方法
    @PreAuthorize("hasAuthority('brand')")
    @PostMapping("/update")
    public Result update(@RequestBody Brand brand){
        brandService.update(brand);
        return  new Result();
    }

    //  品牌删除方法
    @PreAuthorize("hasAuthority('brand')")
    @GetMapping("/delete")
    public Result delete(Integer id){
        brandService.delete(id);
        return new Result();
    }
}
