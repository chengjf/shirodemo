package com.chengjf.shiro.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Created by jeff on 2017/9/17.
 */

@Configuration
@MapperScan("com.chengjf.shiro.demo.shiro.mapper")
public class MybatisConfig implements ConfigurationCustomizer {
    @Override
    public void customize(org.apache.ibatis.session.Configuration configuration) {
        configuration.addMappers("com.chengjf.shiro.demo.shiro.mapper");
    }
}
