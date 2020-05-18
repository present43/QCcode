package com.qingcheng.dao;

import com.qingcheng.pojo.goods.Brand;
import tk.mybatis.mapper.common.Mapper;

//继承通用Mapper 那么这个接口就具有了CRUD的整套方法
//品牌管理的TK.Mapper接口  （映射  这是数据访问层接口 下一层是实体类层）
// Mapper是一个工具类不是接口 作用是访问连接数据库 并获得相应的数据
public interface BrandMapper extends Mapper<Brand> {

}
