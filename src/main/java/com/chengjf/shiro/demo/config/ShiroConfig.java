package com.chengjf.shiro.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jeff on 2017/9/17.
 */


public class ShiroConfig {

    private static Logger log = LoggerFactory.getLogger(ShiroConfig.class);


//    @Bean
//    protected StormtrooperDao stormtrooperDao() {
//        return new DefaultStormtrooperDao();
//    }

//    @Bean
//    public Authenticator authenticator(){
//        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
//        Set<Realm> realms = new HashSet<>();
//        realms.add(realm());
//        authenticator.setRealms(realms);
//        return authenticator;
//    }
//
//    @Bean("realm")
//    public UserRealm userRealm() {
//
//        UserRealm realm = new UserRealm();
//
//        realm.setCachingEnabled(true);
//        return realm;
//    }

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
//    @Bean
//    public SecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        //设置realm.
//        securityManager.setRealm(realm());
//        // 自定义缓存实现 使用redis
//        //securityManager.setCacheManager(cacheManager());
//        // 自定义session管理 使用redis
//        securityManager.setSessionManager(sessionManager());
//        return securityManager;
//    }
//
//
//    @Bean("shiroFilter")
//    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
//        shiroFilter.setSecurityManager(securityManager);
//        shiroFilter.setLoginUrl("/login.html");
////        shiroFilter.setSuccessUrl("/index.html");
//        shiroFilter.setUnauthorizedUrl("/");
//
//        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/public/**", "anon");
//        filterMap.put("/webjars/**", "anon");
//        filterMap.put("/api/**", "anon");
//
//        //swagger配置
//        filterMap.put("/swagger**", "anon");
//        filterMap.put("/v2/api-docs", "anon");
//        filterMap.put("/swagger-resources/configuration/ui", "anon");
//
//        filterMap.put("/login.html", "anon");
//        filterMap.put("/sys/login", "anon");
//        filterMap.put("/captcha.jpg", "anon");
//        filterMap.put("/**", "authc");
//        shiroFilter.setFilterChainDefinitionMap(filterMap);
//
//        return shiroFilter;
//    }
//
////    @Bean
////    public CacheManager cacheManager() {
////        return new MemoryConstrainedCacheManager();
////    }
//
//
//    @Bean(name = "lifecycleBeanPostProcessor")
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
//        proxyCreator.setProxyTargetClass(true);
//        return proxyCreator;
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }
//
//    @Bean(name = "sessionManager")
//    public SessionManager sessionManager() {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
//        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
//        sessionManager.setSessionValidationSchedulerEnabled(true);
//        sessionManager.setSessionIdUrlRewritingEnabled(false);
//        return sessionManager;
//    }
}
