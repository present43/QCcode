package com.qingcheng.pojo.system;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

//   管理员与角色中间表
@Table(name = "tb_admin_role")
public class AdminRole implements Serializable {
    @Id
    private Integer admionId;
    @Id
    private Integer roleId;

    public AdminRole(Integer admionId, Integer roleId) {
        this.admionId = admionId;
        this.roleId = roleId;
    }

    public Integer getAdmionId() {
        return admionId;
    }

    public void setAdmionId(Integer admionId) {
        this.admionId = admionId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
