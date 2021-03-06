package com.chengjf.shiro.demo.shiro.entity;

import java.util.Date;
import java.util.List;

public class SysMenuEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_menu.menu_id
     *
     * @mbg.generated
     */
    private Long menuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_menu.parent_id
     *
     * @mbg.generated
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_menu.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_menu.url
     *
     * @mbg.generated
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_menu.perms
     *
     * @mbg.generated
     */
    private String perms;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_menu.type
     *
     * @mbg.generated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_menu.icon
     *
     * @mbg.generated
     */
    private String icon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_menu.order_num
     *
     * @mbg.generated
     */
    private Integer orderNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_menu.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_menu.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    private List<SysMenuEntity> list;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_menu.menu_id
     *
     * @return the value of t_sys_menu.menu_id
     * @mbg.generated
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_menu.menu_id
     *
     * @param menuId the value for t_sys_menu.menu_id
     * @mbg.generated
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_menu.parent_id
     *
     * @return the value of t_sys_menu.parent_id
     * @mbg.generated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_menu.parent_id
     *
     * @param parentId the value for t_sys_menu.parent_id
     * @mbg.generated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_menu.name
     *
     * @return the value of t_sys_menu.name
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_menu.name
     *
     * @param name the value for t_sys_menu.name
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_menu.url
     *
     * @return the value of t_sys_menu.url
     * @mbg.generated
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_menu.url
     *
     * @param url the value for t_sys_menu.url
     * @mbg.generated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_menu.perms
     *
     * @return the value of t_sys_menu.perms
     * @mbg.generated
     */
    public String getPerms() {
        return perms;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_menu.perms
     *
     * @param perms the value for t_sys_menu.perms
     * @mbg.generated
     */
    public void setPerms(String perms) {
        this.perms = perms == null ? null : perms.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_menu.type
     *
     * @return the value of t_sys_menu.type
     * @mbg.generated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_menu.type
     *
     * @param type the value for t_sys_menu.type
     * @mbg.generated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_menu.icon
     *
     * @return the value of t_sys_menu.icon
     * @mbg.generated
     */
    public String getIcon() {
        return icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_menu.icon
     *
     * @param icon the value for t_sys_menu.icon
     * @mbg.generated
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_menu.order_num
     *
     * @return the value of t_sys_menu.order_num
     * @mbg.generated
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_menu.order_num
     *
     * @param orderNum the value for t_sys_menu.order_num
     * @mbg.generated
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_menu.create_time
     *
     * @return the value of t_sys_menu.create_time
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_menu.create_time
     *
     * @param createTime the value for t_sys_menu.create_time
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_menu.update_time
     *
     * @return the value of t_sys_menu.update_time
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_menu.update_time
     *
     * @param updateTime the value for t_sys_menu.update_time
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<SysMenuEntity> getList() {
        return list;
    }

    public void setList(List<SysMenuEntity> list) {
        this.list = list;
    }
}