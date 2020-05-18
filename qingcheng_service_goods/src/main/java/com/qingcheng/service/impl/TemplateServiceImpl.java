package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingcheng.dao.TemplateMapper;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.pojo.goods.Template;
import com.qingcheng.service.goods.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class TemplateServiceImpl implements TemplateService {

    //Bean 注入服务
    @Autowired
    private TemplateMapper templateMapper;

    //查询所有
    @Override
    public List<Template> findAll() {
        return templateMapper.selectAll();
    }

    //分页查询
    @Override
    public PageResult<Template> findPage(int page, int size) {
        //设置分页数据
        PageHelper.startPage(page, size);
        //获得总记录数
        Page<Template> pageResult = (Page<Template>) templateMapper.selectAll();
        //返回封装结果集
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }

    //模板条件分页查询
    @Override
    public PageResult<Template> findPage(Map<String, Object> searchMap, int page, int size) {
        //设置分页数据
        PageHelper.startPage(page, size);
        //  根据传递过来的searchMap 构建一个example对象
        Example example = createExample(searchMap);
        //获得总记录数
        Page<Template> pageResult = (Page<Template>) templateMapper.selectByExample(example);
        //返回查询结果
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }

    //新增模板 方法
    @Override
    public void add(Template template) {
        //新增一个模板 参数和规格肯定是为零的  也就是初始化
        template.setParaNum(0);
        template.setSpecNum(0);
        templateMapper.insert(template);
    }

    //修改模板方法
    @Override
    public void update(Template template) {
        templateMapper.updateByPrimaryKeySelective(template);
    }

    //判断有没有下级列表 ，如果有则不能删除
    @Override
    public void delete(Integer id) {
        Example example = new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();

    }


    /*
       模板列 模糊查询的封装逻辑
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 模板名称
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }
            // 模板id
            if (searchMap.get("id") != null) {
                criteria.andEqualTo("id", searchMap.get("id"));
            }

        }
        return example;
    }

}
