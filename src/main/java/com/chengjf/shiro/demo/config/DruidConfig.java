package com.chengjf.shiro.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by jeff on 2017/10/2.
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
@EnableTransactionManagement
public class DruidConfig {

    private static final Logger logger = LoggerFactory.getLogger(DruidConfig.class);

    private static Properties getConnectionProperties() {
        Properties properties = new Properties();
        properties.setProperty("druid.stat.mergeSql", "true");
        properties.setProperty("druid.stat.slowSqlMillis", "5000");
        return properties;
    }

    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean(name = "dataSource")
    @Primary
    public DruidDataSource dataSource(DataSourceProperties properties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.determineDriverClassName());
        dataSource.setUrl(properties.determineUrl());
        dataSource.setUsername(properties.determineUsername());
        dataSource.setPassword(properties.determinePassword());
        DatabaseDriver databaseDriver = DatabaseDriver
                .fromJdbcUrl(properties.determineUrl());
        String validationQuery = databaseDriver.getValidationQuery();
        if (validationQuery != null) {
            dataSource.setTestOnBorrow(true);
            dataSource.setValidationQuery(validationQuery);
        }
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        try {
            dataSource.setFilters("stat,wall,log4j");
        } catch (SQLException e) {
            logger.error("Druid config set filters error:{}", e);
        }
        dataSource.setConnectProperties(getConnectionProperties());

        return dataSource;
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }
}
