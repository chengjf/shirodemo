package com.chengjf.shiro.demo.config;


import com.chengjf.shiro.demo.common.model.ErrorMessage;
import com.chengjf.shiro.demo.controllers.NotFoundException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by jeff on 2017/9/17.
 */

@Configuration
@EnableAutoConfiguration
@SpringBootConfiguration
@ControllerAdvice
public class WebMvcConfig extends WebMvcConfigurationSupport {

    private static Logger log = LoggerFactory.getLogger(WebMvcConfig.class);

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
}
