package com.qingcheng.service.goods;

import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Para;
import com.qingcheng.pojo.goods.Spec;

import java.util.List;
import java.util.Map;

public interface ParaService {

    //查询所有
    List<Para> findAll();

    //参数条件 分页查询
    PageResult<Para> findPage(Map<String, Object> searchMap, int page, int size);

    //根据ID查询
    Para findByID(Integer id);

    //修改方法
    void update(Para para);


    //新增方法
    void add(Para para);

    //删除方法
    void delete(Integer id);
}
