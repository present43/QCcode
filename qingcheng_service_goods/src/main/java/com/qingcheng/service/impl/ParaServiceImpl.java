package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingcheng.dao.ParaMapper;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Para;
import com.qingcheng.service.goods.ParaService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class ParaServiceImpl implements ParaService {

    //注入Mapper
    @Autowired
    ParaMapper paraMapper;

    //查询所有
    @Override
    public List<Para> findAll() {
        return paraMapper.selectAll();
    }

    //模糊分页查询
    @Override
    public PageResult<Para> findPage(Map<String, Object> searchMap, int page, int size) {
        //设置分页数据
        PageHelper.startPage(page, size);
        //根据传递过来的searchMap 构建一个example对象
        Example example = createExample(searchMap);
        //获得总记录数
        Page<Para> pageResult = (Page<Para>) paraMapper.selectByExample(example);
        //返回结果集
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }

    //主键查询
    @Override
    public Para findByID(Integer id) {
        return paraMapper.selectByPrimaryKey(id);
    }

    //更新 修改
    @Override
    public void update(Para para) {
        paraMapper.updateByPrimaryKeySelective(para);
    }

    //添加
    @Override
    public void add(Para para) {
        paraMapper.insert(para);
    }

    //主键删除
    @Override
    public void delete(Integer id) {
        paraMapper.deleteByPrimaryKey(id);
    }


    /*
       模糊查询的封装逻辑
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Para.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 参数名称
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }
            // 规格选项
            if (searchMap.get("options") != null && !"".equals(searchMap.get("options"))) {
                criteria.andLike("options", "%" + searchMap.get("options") + "%");
            }

            // 品牌id
            if (searchMap.get("id") != null) {
                criteria.andEqualTo("id", searchMap.get("id"));
            }
            // 排序
            if (searchMap.get("seq") != null) {
                criteria.andEqualTo("seq", searchMap.get("seq"));
            }
            // 模板ID
            if (searchMap.get("templateId") != null) {
                criteria.andEqualTo("templateId", searchMap.get("templateId"));
            }

        }
        return example;
    }
}
