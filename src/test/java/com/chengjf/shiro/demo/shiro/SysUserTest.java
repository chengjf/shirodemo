package com.chengjf.shiro.demo.shiro;

import com.chengjf.shiro.demo.SpringBootApp;
import com.chengjf.shiro.demo.shiro.entity.SysUserEntity;
import com.chengjf.shiro.demo.shiro.service.SysUserService;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jeff on 2017/9/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootApp.class,})
public class SysUserTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void createTest(){

        SysUserEntity entity = sysUserService.queryObject(1L);
        Assert.notNull(entity);
    }
}
