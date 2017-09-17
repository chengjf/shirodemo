package com.chengjf.shiro.demo.shiro.mapper;

import com.chengjf.shiro.demo.shiro.entity.SysUserEntity;
import com.chengjf.shiro.demo.shiro.entity.SysUserEntityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbg.generated
     */
    long countByExample(SysUserEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbg.generated
     */
    int deleteByExample(SysUserEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbg.generated
     */
    int insert(SysUserEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbg.generated
     */
    int insertSelective(SysUserEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbg.generated
     */
    List<SysUserEntity> selectByExample(SysUserEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbg.generated
     */
    SysUserEntity selectByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysUserEntity record, @Param("example") SysUserEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysUserEntity record, @Param("example") SysUserEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysUserEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysUserEntity record);

    List<String> queryAllPerms(Long userId);

    List<Long> queryAllMenuId(Long userId);
}