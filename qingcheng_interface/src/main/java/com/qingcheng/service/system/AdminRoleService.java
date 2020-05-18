package com.qingcheng.service.system;

import com.qingcheng.pojo.system.AdminRole;

import java.util.List;

public interface AdminRoleService {

    //查询所有 的管理员ID 也对应的角色ID
    List<AdminRole> findAll();

    //添加  对应的联系ID
    void add(AdminRole adminRole);


}
