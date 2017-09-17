package com.chengjf.shiro.demo;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootApp.class})
public class MainTest {


//    @Autowired
//    private IHelloService helloService;
//
//    @Before
//    public void init(){
//        System.out.println("init");
//    }
//
//    @After
//    public void destroy(){
//        System.out.println("destroy");
//    }
//
//    @Test
//    public void test(){
//        System.out.println("test");
//        System.out.println(helloService.sayHello("chengjf"));
//
//        Assert.assertEquals("Hello chengjf",helloService.sayHello("chengjf"));
//    }
}
