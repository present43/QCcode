package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qingcheng.dao.BrandMapper;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

//Service_interface 层的实现层
@Service
public class BrandServiceImpl implements BrandService {

    //在服务层要去调用数据访问层

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    // 分页
    @Override
    public PageResult<Brand> findPage(int page, int size) {
        //设置分页数据
        PageHelper.startPage(page, size);
        //获得总记录数
        Page<Brand> pageResult = (Page<Brand>) brandMapper.selectAll();
        //返回结果集
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }

    //条件查询
    @Override
    public List<Brand> findList(Map<String, Object> searchMap) {

        //  Example example = new Example(Brand.class);
        //  根据传递过来的searchMap 构建一个example对象

        Example example = createExample(searchMap);
        return brandMapper.selectByExample(example);
    }

    // 品牌分页条件查询  方法的重载
    @Override
    public PageResult<Brand> findPage(Map<String, Object> searchMap, int page, int size) {
        //设置分页数据
        PageHelper.startPage(page, size);
        //  根据传递过来的searchMap 构建一个example对象
        Example example = createExample(searchMap);
        //获得总记录数
        Page<Brand> pageResult = (Page<Brand>) brandMapper.selectByExample(example);
        //返回结果集
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }

    //根据ID查询
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    //   品牌新增方法
    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    //  品牌修改方法
    @Override
    public void update(Brand brand) {
        //根据主键进行更新  此方法与brandMapper.updateByPrimaryKey() 的区别在与它会忽略null值
        //不会把null值 存入数据库
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    // 根据主键删除

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);

    }

    /*
       模糊查询的封装逻辑
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 品牌名称
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }

            // 品牌的首字母
            if (searchMap.get("letter") != null && !"".equals(searchMap.get("letter"))) {
                criteria.andLike("letter", "%" + searchMap.get("letter") + "%");
            }

            // 品牌id
            if (searchMap.get("id") != null) {
                criteria.andEqualTo("id", searchMap.get("id"));
            }
            // 排序
            if (searchMap.get("seq") != null) {
                criteria.andEqualTo("seq", searchMap.get("seq"));
            }

        }
        return example;
    }

    //把条件查询的逻辑封装 成一个方法   ? 错误代码 "".equals(searchMap.get("name"))  忘记取反了 导致里面的语句不执行 直接返回了example
//    private Example createExampleE(Map<String, Object> searchMap) {
//        //   Example条件查询的封装对象   条件查询
//        Example example = new Example(Brand.class);
//        //    再次封装 加条件
//        Example.Criteria criteria = example.createCriteria();
//
//        //先判断 数据的有效性
//        if (searchMap != null) {
//
//            if (searchMap.get("name") != null && "".equals(searchMap.get("name"))) {
//                //    根据属性name 进行模糊查询
//                criteria.andLike("name", "%" + (String) searchMap.get("name") + "%");
//            }
//            if (searchMap.get("letter") != null && "".equals(searchMap.get("letter"))) {
//                //    根据属性letter(首字母) 进行精确查询
//                criteria.andEqualTo("letter", (String) searchMap.get("letter"));
//            }
//        }
//        return example;
//    }
}
