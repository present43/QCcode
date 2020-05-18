package com.qingcheng.service.goods;

import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Template;

import java.util.List;
import java.util.Map;

//模板列的整套方法
public interface TemplateService {

    //查询所有模板的方法
    List<Template> findAll();

    //分页查询
    PageResult<Template> findPage(int page, int size);

    //模板条件 分页查询
    PageResult<Template> findPage(Map<String, Object> searchMap, int page, int size);

    //模板新增方法
     void add(Template template);

    //模板修改方法
    void update(Template template);

    //模板删除方法
    void  delete(Integer id);
}
