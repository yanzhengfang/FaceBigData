package com.koocloud.facerecognition.showdata.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 配置数据库连接池信息
 */
@Configuration
public class DataSourceConfig {


    @Bean(name = "mainDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mainsource")
    @Primary
    public DataSource mainDataSource(){
        return new DruidDataSource();
    }


    @Bean(name = "sptDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.sptsource")
    public DataSource sptDataSource(){
        return new DruidDataSource();
    }
}
