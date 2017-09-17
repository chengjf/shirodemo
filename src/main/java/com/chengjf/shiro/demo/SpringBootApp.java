package com.chengjf.shiro.demo;

import com.chengjf.shiro.demo.common.dao.DefaultStormtrooperDao;
import com.chengjf.shiro.demo.common.dao.StormtrooperDao;
import com.chengjf.shiro.demo.common.model.ErrorMessage;
import com.chengjf.shiro.demo.controllers.NotFoundException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@SpringBootApplication
public class SpringBootApp {

    private static Logger log = LoggerFactory.getLogger(SpringBootApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleException(UnauthenticatedException e, Model model) {
        log.debug("{} was thrown", e.getClass(), e);

        model.addAttribute("errors", new ErrorMessage(HttpStatus.UNAUTHORIZED.toString(), "UNAUTHORIZED"));
        return "error";
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleException(AuthorizationException e, Model model) {
         log.debug("{} was thrown", e.getClass(), e);
        model.addAttribute("errors", new ErrorMessage(HttpStatus.FORBIDDEN.toString(), "FORBIDDEN"));
        return "error";
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(NotFoundException e, Model model) {
        String id = e.getMessage();

        model.addAttribute("errors", new ErrorMessage(HttpStatus.NOT_FOUND.toString(), "Trooper Not Found: " + id + ", why aren't you at your post? " + id + ", do you copy?"));
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("errors", new ErrorMessage("500", e.getMessage()));
        return "error";
    }

    @Bean
    protected StormtrooperDao stormtrooperDao() {
        return new DefaultStormtrooperDao();
    }

    @Bean
    public Realm realm() {

        // uses 'classpath:shiro-users.properties' by default
        PropertiesRealm realm = new PropertiesRealm();

        // Caching isn't needed in this example, but we can still turn it on
        realm.setCachingEnabled(true);
        return realm;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // use permissive to NOT require authentication, our controller Annotations will decide that
        chainDefinition.addPathDefinition("/login.html", "authc");
        chainDefinition.addPathDefinition("/logout", "logout");

        chainDefinition.addPathDefinition("/troopers", "authc[permissive]");
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    @Bean
    public CacheManager cacheManager() {
        // Caching isn't needed in this example, but we will use the MemoryConstrainedCacheManager for this example.
        return new MemoryConstrainedCacheManager();
    }

}
