package com.chengjf.shiro.demo.config;

import com.chengjf.shiro.demo.common.dao.DefaultStormtrooperDao;
import com.chengjf.shiro.demo.common.dao.StormtrooperDao;
import com.chengjf.shiro.demo.shiro.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jeff on 2017/9/17.
 */


@Configuration
@EnableAutoConfiguration
@SpringBootConfiguration
@ControllerAdvice
public class ShiroConfig {

    private static Logger log = LoggerFactory.getLogger(ShiroConfig.class);


    @Bean
    protected StormtrooperDao stormtrooperDao() {
        return new DefaultStormtrooperDao();
    }

    @Bean("realm")
    public UserRealm userRealm() {

        UserRealm realm = new UserRealm();

        realm.setCachingEnabled(true);
        return realm;
    }

//    @Bean
//    public Realm realm() {
//        IniRealm realm = new IniRealm("classpath:shiro-users.properties");
//        return realm;
//    }
//
//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//        // use permissive to NOT require authentication, our controller Annotations will decide that
//        chainDefinition.addPathDefinition("/**/favicon.ico", "anon");
//
//        chainDefinition.addPathDefinition("/login.html", "authc");
//        chainDefinition.addPathDefinition("/logout", "logout");
//        chainDefinition.addPathDefinition("/troopers", "authc[permissive]");
//        chainDefinition.addPathDefinition("/**", "authc");
//        return chainDefinition;
//    }
//
////    @Bean(name = "securityManager")
////    public SecurityManager securityManager(Realm realm, SessionManager sessionManager) {
////        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
////        securityManager.setRealm(realm);
////        securityManager.setSessionManager(sessionManager);
////
////        return securityManager;
////    }
//
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(userRealm());
        // 自定义缓存实现 使用redis
        //securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

//    @Bean
//    public FilterRegistrationBean delegatingFilterProxy() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
//        proxy.setTargetFilterLifecycle(true);
//        proxy.setTargetBeanName("shiroFilter");
//        filterRegistrationBean.setFilter(proxy);
//        return filterRegistrationBean;
//    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login.html");
        shiroFilter.setSuccessUrl("/");
        shiroFilter.setUnauthorizedUrl("/403.html");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/public/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/api/**", "anon");


//        filterChainDefinitionMap.put("/login.html", "anon");
        filterChainDefinitionMap.put("/sys/login", "anon");
        filterChainDefinitionMap.put("/captcha.jpg", "anon");
        filterChainDefinitionMap.put("/favicon.ico","anon");
        filterChainDefinitionMap.put("/error","anon");
        filterChainDefinitionMap.put("/logout","logout");


        filterChainDefinitionMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
//
//        Map<String,Filter> filterMap = new LinkedHashMap<>();
//        filterMap.put("authc", new FormAuthenticationFilter());
//
//        shiroFilter.setFilters(filterMap);

        return shiroFilter;
    }
//
////    @Bean
////    public CacheManager cacheManager() {
////        return new MemoryConstrainedCacheManager();
////    }
//

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean(name = "sessionManager")
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);

        return hashedCredentialsMatcher;
    }
    @Bean(name = "rememberMeCookie")
    public SimpleCookie getRememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }
    @Bean
    public CookieRememberMeManager getRememberManager(){
        CookieRememberMeManager meManager = new CookieRememberMeManager();
        meManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        meManager.setCookie(getRememberMeCookie());
        return meManager;
    }
}
