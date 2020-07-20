package com.koocloud.facerecognition.showdata.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * 配置数据连接信息 主数据库信息
 * 保存所有的信息
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryDataBaseMain", // 配置连接工厂
        transactionManagerRef = "transactionManagerDatabaseMain", // 配置事物管理器
        basePackages = {"com.koocloud.facerecognition.showdata.dao.main"} // 设置dao所在位置

)
public class MainDataConfig {

    @Resource
    @Qualifier("mainDataSource")
    private DataSource mainDataSource;



    @Bean(name = "entityManagerFactoryDataBaseMain")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryDataBaseMain(EntityManagerFactoryBuilder builder) {
        return builder
                // 设置数据源
                .dataSource(mainDataSource)
                //设置实体类所在位置.扫描所有带有 @Entity 注解的类
                .packages("com.koocloud.facerecognition.showdata.entity.main")
                // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
                // Repository就能用它来创建 EntityManager 了,然后 EntityManager 就可以针对数据库执行操作
                .persistenceUnit("databaseMainPersistenceUnit")
                .build();

    }


    /**
     * 配置事物管理器
     *
     * @param builder
     * @return
     */
    @Bean(name = "transactionManagerDatabaseMain")
    @Primary
    PlatformTransactionManager transactionManagerDatabaseMain(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryDataBaseMain(builder).getObject());
    }



    @Bean(name = "mainEntityManager")
    @Primary
    public EntityManager mainEntityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryDataBaseMain(builder).getObject().createEntityManager();
    }




}
