package com.qingcheng.pojo.system.vo;

import com.qingcheng.pojo.system.Admin;
import com.qingcheng.pojo.system.AdminRole;

import java.io.Serializable;
import java.util.List;

//组合实体类 包含了管理员实体类和角色ID二个属性
public class AdminVO implements Serializable {
    //管理员 实体类信息
    private Admin admin;
    private Integer rokeId;

    public Integer getRokeId() {
        return rokeId;
    }

    public void setRokeId(Integer rokeId) {
        this.rokeId = rokeId;
    }

    //角色ID 集合信息
    private List<AdminRole> adminRoleList;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<AdminRole> getAdminRoleList() {
        return adminRoleList;
    }

    public void setAdminRoleList(List<AdminRole> adminRoleList) {
        this.adminRoleList = adminRoleList;
    }
}
