package com.qingcheng.service.goods;

import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Spec;

import java.util.List;
import java.util.Map;

public interface SpecService  {
    //查询所有
    List<Spec> findAll();

    //参数条件 分页查询
    PageResult<Spec> findPage(Map<String, Object> searchMap, int page, int size);

    //根据ID查询
    Spec findByID(Integer id);

    //修改方法
    void update(Spec spec);


    //新增方法
    void add(Spec spec);

    //删除方法
    void delete(Integer id);

}
