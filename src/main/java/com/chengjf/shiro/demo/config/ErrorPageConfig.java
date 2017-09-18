package com.chengjf.shiro.demo.config;


import com.chengjf.shiro.demo.common.model.ErrorMessage;
import com.chengjf.shiro.demo.controllers.NotFoundException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@Configuration
@ControllerAdvice
public class ErrorPageConfig {

    public static final Logger logger = LoggerFactory.getLogger(ErrorPageConfig.class);

    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
        return new MyCustomizer();
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleException(UnauthenticatedException e, Model model) {
        logger.debug("{} was thrown", e.getClass(), e);

        model.addAttribute("error", "UNAUTHORIZED");
        model.addAttribute("status", HttpStatus.UNAUTHORIZED.toString());
        return "error";
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleException(AuthorizationException e, Model model) {
        logger.debug("{} was thrown", e.getClass(), e);

        model.addAttribute("error", "FORBIDDEN");
        model.addAttribute("status", HttpStatus.FORBIDDEN.toString());
        return "error";
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(NotFoundException e, Model model) {
        String id = e.getMessage();


        model.addAttribute("error", "Trooper Not Found: " + id + ", why aren't you at your post? " + id + ", do you copy?");
        model.addAttribute("status", HttpStatus.NOT_FOUND.toString());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e, Model model) {

        model.addAttribute("error", "INTERNAL_SERVER_ERROR");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return "error";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String requestHandlingNoHandlerFound(Exception e, Model model) {
        model.addAttribute("error", "NOT_FOUND");
        model.addAttribute("status", HttpStatus.NOT_FOUND.toString());
        return "error";
    }

    private static class MyCustomizer implements EmbeddedServletContainerCustomizer {

        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
//            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error"));
//            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error"));
        }

    }
}
