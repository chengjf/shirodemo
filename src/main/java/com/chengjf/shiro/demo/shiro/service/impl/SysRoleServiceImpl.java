package com.chengjf.shiro.demo.shiro.service.impl;

import com.chengjf.shiro.demo.config.Constant;
import com.chengjf.shiro.demo.shiro.entity.SysRoleEntity;
import com.chengjf.shiro.demo.shiro.entity.SysRoleEntityExample;
import com.chengjf.shiro.demo.shiro.mapper.SysRoleEntityMapper;
import com.chengjf.shiro.demo.shiro.service.SysRoleMenuService;
import com.chengjf.shiro.demo.shiro.service.SysRoleService;
import com.chengjf.shiro.demo.shiro.service.SysUserRoleService;
import com.chengjf.shiro.demo.shiro.service.SysUserService;
import com.chengjf.shiro.demo.shiro.util.AdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 角色
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:45:12
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleEntityMapper sysRoleEntityMapper;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public SysRoleEntity queryObject(Long roleId) {

        return sysRoleEntityMapper.selectByPrimaryKey(roleId);
    }

//	@Override
//	public List<SysRoleEntity> queryList(Map<String, Object> map) {
//		return sysRoleDao.queryList(map);
//	}

//	@Override
//	public int queryTotal(Map<String, Object> map) {
//		return sysRoleDao.queryTotal(map);
//	}

    @Override
    @Transactional
    public void save(SysRoleEntity role) {

        sysRoleEntityMapper.insert(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public void update(SysRoleEntity role) {
        sysRoleEntityMapper.updateByPrimaryKeySelective(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(List<Long> roleIds) {
        SysRoleEntityExample example = new SysRoleEntityExample();
        SysRoleEntityExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdIn(roleIds);
        sysRoleEntityMapper.deleteByExample(example);

        sysRoleMenuService.deleteByRoleId(roleIds);

        sysUserRoleService.deleteByRoleIds(roleIds);

    }


    @Override
    public List<Long> queryRoleIdList(Long createUserId) {

        SysRoleEntityExample example = new SysRoleEntityExample();
        SysRoleEntityExample.Criteria criteria = example.createCriteria();
        criteria.andCreateUserIdEqualTo(createUserId);
        List<SysRoleEntity> sysRoleEntities = sysRoleEntityMapper.selectByExample(example);
        List<Long> collect = sysRoleEntities.stream().map(o -> o.getRoleId()).collect(Collectors.toList());
        return collect;
    }

    /**
     * 检查权限是否越权
     */
    private void checkPrems(SysRoleEntity role) {
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if (role.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户所拥有的菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());

        //判断是否越权
        if (!menuIdList.containsAll(role.getMenuIdList())) {
            throw new AdminException("新增角色的权限，已超出你的权限范围");
        }
    }
}
