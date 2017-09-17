package com.chengjf.shiro.demo;


import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootApp.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShiroTest {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void test() {
//        AuthenticationToken token = new StatelessToken();
        AuthenticationToken token = new UsernamePasswordToken("x", "x");
        AuthenticationInfo authenticate = securityManager.authenticate(token);
        Object credentials = authenticate.getCredentials();
        System.out.println(credentials);
    }

//    @Test
//    public void testStateless() {
//        AuthenticationToken token = new StatelessToken("xxx", "sssss");
//
//        AuthenticationInfo authenticate = securityManager.authenticate(token);
//        Object credentials = authenticate.getCredentials();
//        System.out.println(credentials);
//    }
//
//    @Test
//    public void testPermission() {
//        PrincipalCollection principalCollection = new SimplePrincipalCollection();
//
//        Permission permission = new MyPermission();
//        securityManager.isPermitted(principalCollection, permission);
//    }
//

    @Test
    public void testPermissionAnnotation() {

        ResponseEntity<String> forEntity = testRestTemplate.getForEntity("/v3/hello", String.class);
        System.out.println(forEntity.getBody());
    }

}
