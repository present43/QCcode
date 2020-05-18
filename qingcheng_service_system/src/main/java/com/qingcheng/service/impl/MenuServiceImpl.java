package com.qingcheng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qingcheng.dao.MenuMapper;
import com.qingcheng.pojo.system.Menu;
import com.qingcheng.service.system.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    //注入连接
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Map> findAllMenu() {
        //首先把符合条件 的菜单查询出来(列表) ,通过内存判断筛选出符合条件的记录(每一级的菜单列表)
        //把数据库里的菜单信息一次查询到内存当中
        List<Menu> menuList = menuMapper.selectAll();
        //进行筛选
        return findMenuListByParentId(menuList, "0");
    }

    //不和数据库进行交互,直接从内存中筛选
    private List<Map> findMenuListByParentId(List<Menu> menuList, String parentId) {
        List<Map> mapLista = new ArrayList<Map>();
        //循环 menuList
        for (Menu menu : menuList) {
            if (menu.getParentId().equals(parentId)) {
                Map map = new HashMap();
                //封装成前端需要的格式
                map.put("path", menu.getId());
                map.put("title", menu.getName());
                map.put("icon", menu.getIcon());
                map.put("linkUrl", menu.getUrl());
                //递归 逐层调用
                map.put("children", findMenuListByParentId(menuList, menu.getId()));
                //添加完整数据
                mapLista.add(map);
            }
        }

        return mapLista;
    }
}
