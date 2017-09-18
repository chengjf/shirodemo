package com.chengjf.shiro.demo.shiro.service.impl;


import com.chengjf.shiro.demo.shiro.entity.SysRoleMenuEntity;
import com.chengjf.shiro.demo.shiro.entity.SysRoleMenuEntityExample;
import com.chengjf.shiro.demo.shiro.mapper.SysRoleMenuEntityMapper;
import com.chengjf.shiro.demo.shiro.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation .Transactional;

import java.util.List;


/**
 * 角色与菜单对应关系
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:44:35
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuEntityMapper sysRoleMenuEntityMapper;

    @Override
    @Transactional
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        //先删除角色与菜单关系
        SysRoleMenuEntityExample example = new SysRoleMenuEntityExample();
        SysRoleMenuEntityExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        sysRoleMenuEntityMapper.deleteByExample(example);

        if (menuIdList.size() == 0) {
            return;
        }

        //保存角色与菜单关系
        example = new SysRoleMenuEntityExample();
        criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        criteria.andMenuIdIn(menuIdList);

        menuIdList.forEach(l -> {
            SysRoleMenuEntity entity = new SysRoleMenuEntity();
            entity.setRoleId(roleId);
            entity.setMenuId(l);
            sysRoleMenuEntityMapper.insert(entity);
        });

    }

    @Override
    public void deleteByRoleId(List<Long> roleIds) {
        SysRoleMenuEntityExample example = new SysRoleMenuEntityExample();
        SysRoleMenuEntityExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdIn(roleIds);
        sysRoleMenuEntityMapper.deleteByExample(example);
    }

    @Override
    public void deleteByMenuIds(List<Long> menuIdList) {
        SysRoleMenuEntityExample example = new SysRoleMenuEntityExample();
        SysRoleMenuEntityExample.Criteria criteria = example.createCriteria();
        criteria.andMenuIdIn(menuIdList);
        sysRoleMenuEntityMapper.deleteByExample(example);
    }

    //	@Override
//	public List<Long> queryMenuIdList(Long roleId) {
//		SysRoleMenuEntityExample example = new SysRoleMenuEntityExample();
//		SysRoleMenuEntityExample.Criteria criteria = example.createCriteria();
//		criteria.andRoleIdEqualTo(roleId);
//
//		return sysRoleMenuEntityMapper.selectByExample(example);
//	}

}
