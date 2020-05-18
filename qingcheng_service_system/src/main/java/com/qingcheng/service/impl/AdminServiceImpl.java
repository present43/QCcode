package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qingcheng.dao.AdminMapper;
import com.qingcheng.dao.AdminRoleMapper;
import com.qingcheng.pojo.system.Admin;
import com.qingcheng.pojo.system.AdminRole;
import com.qingcheng.pojo.system.vo.AdminVO;
import com.qingcheng.service.system.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    //注入Mapper
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    //查询所有
    @Override
    public List<Admin> findAll() {
        return adminMapper.selectAll();
    }

    // 条件查询
    @Override
    public List<Admin> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return adminMapper.selectByExample(example);
    }

    //主键查询
    @Override
    public Admin findById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    //添加管理员
    @Override
    public void add(Admin admin) {

        // 把组合实体类的中 关于管理员的信息存入 管理员表当中
        adminMapper.insert(admin);
    }

    //更新
    @Override
    public void update(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    //删除
    @Override
    public void delete(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    //登录名 查权限  登录，名怎么拿？ 在用户服务里面拿
    @Override
    public List<String> Authority(String name) {
        List<String> authority = adminMapper.findAuthority(name);
        return authority;
    }



    /**
     * 构建查询条件
     *
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 用户名
            if (searchMap.get("loginName") != null && !"".equals(searchMap.get("loginName"))) {
                //criteria.andLike("loginName","%"+searchMap.get("loginName")+"%");
                criteria.andEqualTo("loginName", searchMap.get("loginName"));
            }
            // 密码
            if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
                criteria.andLike("password", "%" + searchMap.get("password") + "%");
            }
            // 状态
            if (searchMap.get("status") != null && !"".equals(searchMap.get("status"))) {
                criteria.andEqualTo("status", searchMap.get("status"));
            }
            // id
            if (searchMap.get("id") != null) {
                criteria.andEqualTo("id", searchMap.get("id"));
            }

        }
        return example;
    }

}
