package com.qingcheng.service.system;

import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.system.Admin;
import com.qingcheng.pojo.system.vo.AdminVO;

import java.util.List;
import java.util.Map;

public interface AdminService {

    //管理员 查询所有
    public List<Admin> findAll();


    //模糊查询
    public List<Admin> findList(Map<String,Object> searchMap);

    //主键查询
    public Admin findById(Integer id);

    //添加
    public void add(Admin admin);

    //更新
    public void update(Admin admin);

    //删除
    public void delete(Integer id);

    //根据用户名 查询管理员对应的权限
    List<String> Authority(String name);
}
