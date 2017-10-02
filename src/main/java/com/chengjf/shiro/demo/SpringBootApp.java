package com.chengjf.shiro.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ControllerAdvice;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringBootApp {

    private static Logger log = LoggerFactory.getLogger(SpringBootApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }


}
