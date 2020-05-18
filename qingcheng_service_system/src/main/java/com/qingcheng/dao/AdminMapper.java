package com.qingcheng.dao;

import com.qingcheng.pojo.system.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

//数据访问层接口
public interface AdminMapper extends Mapper<Admin> {

    //登录名查询权限
    @Select(value = "SELECT res_key FROM tb_resource WHERE id IN ( " +
            "select resource_id from tb_role_resource where role_id IN ( " +
            "SELECT tb_admin_role.role_id FROM tb_admin_role WHERE admin_id   IN( " +
            "SELECT id FROM tb_admin WHERE login_name =#{loginName}  " +
            ") " +
            "    ) " +
            "       )")
    public List<String> findAuthority(@Param("loginName") String loginName);
}
