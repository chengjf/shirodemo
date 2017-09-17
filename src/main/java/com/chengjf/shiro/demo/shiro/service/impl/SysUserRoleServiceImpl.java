package com.chengjf.shiro.demo.shiro.service.impl;


import com.chengjf.shiro.demo.shiro.entity.SysUserRoleEntity;
import com.chengjf.shiro.demo.shiro.entity.SysUserRoleEntityExample;
import com.chengjf.shiro.demo.shiro.mapper.SysUserRoleEntityMapper;
import com.chengjf.shiro.demo.shiro.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 用户与角色对应关系
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:45:48
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleEntityMapper sysUserRoleEntityMapper;

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        if (roleIdList.size() == 0) {
            return;
        }

        //先删除用户与角色关系
        SysUserRoleEntityExample example = new SysUserRoleEntityExample();
        SysUserRoleEntityExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        sysUserRoleEntityMapper.deleteByExample(example);

        //保存用户与角色关系
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roleIdList", roleIdList);
        roleIdList.forEach(l -> {
            SysUserRoleEntity entity = new SysUserRoleEntity();
            entity.setUserId(userId);
            entity.setRoleId(l);
            sysUserRoleEntityMapper.insert(entity);
        });

    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        SysUserRoleEntityExample example = new SysUserRoleEntityExample();
        SysUserRoleEntityExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<SysUserRoleEntity> sysUserRoleEntities = sysUserRoleEntityMapper.selectByExample(example);
        List<Long> collect = sysUserRoleEntities.stream().map(o -> o.getRoleId()).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void delete(Long userId) {
        SysUserRoleEntityExample example = new SysUserRoleEntityExample();
        SysUserRoleEntityExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        sysUserRoleEntityMapper.deleteByExample(example);

    }

    @Override
    public void deleteByRoleIds(List<Long> roleIds) {
        SysUserRoleEntityExample example = new SysUserRoleEntityExample();
        SysUserRoleEntityExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdIn(roleIds);
        sysUserRoleEntityMapper.deleteByExample(example);
    }
}
