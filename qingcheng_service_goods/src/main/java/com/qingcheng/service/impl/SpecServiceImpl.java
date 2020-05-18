package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.qingcheng.dao.SpecMapper;
import com.qingcheng.dao.TemplateMapper;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Spec;
import com.qingcheng.pojo.goods.Template;
import com.qingcheng.service.goods.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = SpecService.class)
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private TemplateMapper templateMapper;

    //查询所有
    @Override
    public List<Spec> findAll() {
        return specMapper.selectAll();
    }

    // 分页模糊查询
    @Override
    public PageResult<Spec> findPage(Map<String, Object> searchMap, int page, int size) {
        //设置分页数据
        PageHelper.startPage(page, size);
        //根据传递过来的searchMap 构建一个example对象
        Example example = createExample(searchMap);
        //获得总记录数
        Page<Spec> pageResult = (Page<Spec>) specMapper.selectByExample(example);
        //返回结果集
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }

    //主键查询
    @Override
    public Spec findByID(Integer id) {
        return specMapper.selectByPrimaryKey(id);
    }

    //修改
    @Override
    public void update(Spec spec) {
        specMapper.updateByPrimaryKeySelective(spec);
    }

    //增加   包含有多个数据访问操作 为了保证数据安全 加入事务注解 @Transactional
    @Override
    @Transactional
    public void add(Spec spec) {
        specMapper.insert(spec);
        //根据spec的模板ID去找到模板对象
        Template template = templateMapper.selectByPrimaryKey(spec.getTemplateId());
        //然后根据模板对象去把规格数量加 1
        template.setSpecNum(template.getSpecNum() + 1);
        //改完后在存回去
        templateMapper.updateByPrimaryKey(template);
    }


    //主键删除  包含有多个数据访问操作 为了保证数据安全 加入事务注解 @Transactional
    @Override
    @Transactional
    public void delete(Integer id) {
        // 获得spec 对象  根据此对象获得 getTemplateId
         Spec spec = specMapper.selectByPrimaryKey(id);
        //根据spec的模板ID去找到模板对象
         Template template = templateMapper.selectByPrimaryKey(spec.getTemplateId());
        //然后根据模板对象去把规格数量减 1
         template.setSpecNum(template.getSpecNum() - 1);
        //改完后在存回去
          templateMapper.updateByPrimaryKey(template);

        specMapper.deleteByPrimaryKey(id);
    }


    /*
       模糊查询的封装逻辑
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Spec.class);
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
