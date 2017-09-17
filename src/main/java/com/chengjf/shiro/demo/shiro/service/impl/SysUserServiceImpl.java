package com.chengjf.shiro.demo.shiro.service.impl;

import com.chengjf.shiro.demo.config.Constant;
import com.chengjf.shiro.demo.shiro.entity.SysUserEntity;
import com.chengjf.shiro.demo.shiro.entity.SysUserEntityExample;
import com.chengjf.shiro.demo.shiro.mapper.SysUserEntityMapper;
import com.chengjf.shiro.demo.shiro.service.SysRoleService;
import com.chengjf.shiro.demo.shiro.service.SysUserRoleService;
import com.chengjf.shiro.demo.shiro.service.SysUserService;
import com.chengjf.shiro.demo.shiro.util.AdminException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 系统用户
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserEntityMapper sysUserEntityMapper;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public List<String> queryAllPerms(Long userId) {

        return sysUserEntityMapper.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {

        return sysUserEntityMapper.queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryByUserName(String username) {
        SysUserEntityExample example = new SysUserEntityExample();
        SysUserEntityExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        return sysUserEntityMapper.selectByExample(example).get(0);
    }

    @Override
    public SysUserEntity queryObject(Long userId) {
        return sysUserEntityMapper.selectByPrimaryKey(userId);
    }

//	@Override
//	public List<SysUserEntity> queryList(Map<String, Object> map){
//		// TODO
//		SysUserEntityExample example = new SysUserEntityExample();
//		SysUserEntityExample.Criteria criteria = example.createCriteria();
//		return sysUserEntityMapper.selectByExample(example);
//	}
//
//	@Override
//	public int queryTotal(Map<String, Object> map) {
//		return sysUserDao.queryTotal(map);
//	}

    @Override
    @Transactional
    public void save(SysUserEntity user) {
        user.setCreateTime(new Date());
        //sha256加密
        user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        sysUserEntityMapper.insert(user);


        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(SysUserEntity user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        }
        sysUserEntityMapper.updateByPrimaryKey(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void deleteBatch(List<Long> userIds) {

    }

//	@Override
//	@Transactional
//	public void deleteBatch(Long[] userId) {
//		sysUserDao.deleteBatch(userId);
//	}

    @Override
    @Transactional
    public int updatePassword(Long userId, String password, String newPassword) {

        SysUserEntityExample example = new SysUserEntityExample();
        SysUserEntityExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andPasswordEqualTo(password);

        List<SysUserEntity> sysUserEntities = sysUserEntityMapper.selectByExample(example);

        if (sysUserEntities != null && sysUserEntities.size() == 1) {
            SysUserEntity entity = sysUserEntities.get(0);
            entity.setPassword(newPassword);
            return sysUserEntityMapper.updateByPrimaryKey(entity);
        }

        return 0;
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUserEntity user) {
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (user.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户创建的角色列表
        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

        //判断是否越权
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new AdminException("新增用户所选角色，不是本人创建");
        }
    }
}
