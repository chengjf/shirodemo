package com.chengjf.shiro.demo.shiro.service.impl;


import com.chengjf.shiro.demo.config.Constant;
import com.chengjf.shiro.demo.shiro.entity.SysMenuEntity;
import com.chengjf.shiro.demo.shiro.entity.SysMenuEntityExample;
import com.chengjf.shiro.demo.shiro.mapper.SysMenuEntityMapper;
import com.chengjf.shiro.demo.shiro.service.SysMenuService;
import com.chengjf.shiro.demo.shiro.service.SysRoleMenuService;
import com.chengjf.shiro.demo.shiro.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuEntityMapper sysMenuEntityMapper;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        SysMenuEntityExample example = new SysMenuEntityExample();
        SysMenuEntityExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        example.setOrderByClause("order_num asc");
        List<SysMenuEntity> menuList = sysMenuEntityMapper.selectByExample(example);

        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for (SysMenuEntity menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        SysMenuEntityExample example = new SysMenuEntityExample();
        SysMenuEntityExample.Criteria criteria = example.createCriteria();
        criteria.andTypeNotEqualTo(2);
        example.setOrderByClause("order_num asc");
        return sysMenuEntityMapper.selectByExample(example);
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if (userId == 1) {
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == 1) {
            List<SysMenuEntity> menuList = queryList();
            permsList = new ArrayList<>(menuList.size());
            for (SysMenuEntity menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserService.queryAllPerms(userId);
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<String>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysMenuEntity queryObject(Long menuId) {
        return sysMenuEntityMapper.selectByPrimaryKey(menuId);
    }


    public List<SysMenuEntity> queryList() {
        SysMenuEntityExample example = new SysMenuEntityExample();
        return sysMenuEntityMapper.selectByExample(null);
    }

//	@Override
//	public int queryTotal(Map<String, Object> map) {
//		return sysMenuEntityMapper.queryTotal(map);
//	}

    @Override
    public void save(SysMenuEntity menu) {
        sysMenuEntityMapper.insert(menu);
    }

    @Override
    public void update(SysMenuEntity menu) {
        sysMenuEntityMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    @Transactional
    public void deleteBatch(List<Long> menuIds) {
        SysMenuEntityExample example = new SysMenuEntityExample();
        SysMenuEntityExample.Criteria criteria = example.createCriteria();
        criteria.andMenuIdIn(menuIds);
        sysMenuEntityMapper.deleteByExample(example);

        //delete role menu
        sysRoleMenuService.deleteByMenuIds(menuIds);
    }

    @Override
    public List<SysMenuEntity> queryUserList(Long userId) {
        return sysMenuEntityMapper.queryUserList(userId);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList) {
        List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

        for (SysMenuEntity entity : menuList) {
            if (entity.getType() == Constant.MenuType.CATALOG.getValue()) {//目录
                List<SysMenuEntity> menuTreeList = getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList);
                entity.setList(menuTreeList);
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
