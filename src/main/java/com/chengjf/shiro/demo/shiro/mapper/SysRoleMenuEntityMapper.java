package com.chengjf.shiro.demo.shiro.mapper;

import com.chengjf.shiro.demo.shiro.entity.SysRoleMenuEntity;
import com.chengjf.shiro.demo.shiro.entity.SysRoleMenuEntityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMenuEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_menu
     *
     * @mbg.generated
     */
    long countByExample(SysRoleMenuEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_menu
     *
     * @mbg.generated
     */
    int deleteByExample(SysRoleMenuEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_menu
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_menu
     *
     * @mbg.generated
     */
    int insert(SysRoleMenuEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_menu
     *
     * @mbg.generated
     */
    int insertSelective(SysRoleMenuEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_menu
     *
     * @mbg.generated
     */
    List<SysRoleMenuEntity> selectByExample(SysRoleMenuEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_menu
     *
     * @mbg.generated
     */
    SysRoleMenuEntity selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_menu
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysRoleMenuEntity record, @Param("example") SysRoleMenuEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_menu
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysRoleMenuEntity record, @Param("example") SysRoleMenuEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_menu
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysRoleMenuEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_role_menu
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysRoleMenuEntity record);
}