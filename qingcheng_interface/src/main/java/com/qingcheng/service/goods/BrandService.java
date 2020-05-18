package com.qingcheng.service.goods;

import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Brand;

import java.util.List;
import java.util.Map;

//service的接口层 商品品牌的整套方法
public interface BrandService {

    //    查询所有品牌的方法
    public List<Brand> findAll();

    //    返回分页数据的方法 page页码 size大小
    public PageResult<Brand> findPage(int page, int size);

    //  品牌条件查询 因为是返回值所以要用Map结构
    public List<Brand> findList(Map<String, Object> searchMap) throws ClassNotFoundException;

    // 品牌分页条件查询  方法的重载
    public PageResult<Brand> findPage(Map<String, Object> searchMap, int page, int size) throws ClassNotFoundException;

    //    根据品牌ID来查询
    public Brand findById(Integer id);

    //    品牌新增方法 只需要保存数据 不要返回
    public void add(Brand brand);

    //  品牌修改方法
    public void update(Brand brand);

    //  品牌删除
    public void delete(Integer id);
}
