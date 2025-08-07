package com.example.clickhouse.config;

import jakarta.persistence.EntityManagerFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@ConditionalOnProperty(name = "spring.datasource.platform.enabled", havingValue = "true", matchIfMissing = false)
@EnableJpaRepositories(
        entityManagerFactoryRef = "platformEntityManagerFactory",
        transactionManagerRef = "platformTransactionManager",
        basePackages = { "com.example.clickhouse.repositories.platform" })
public class PlatformDatasourceConfiguration {


    @Bean(name="platformProperties")
    @ConfigurationProperties("spring.datasource.platform")
    public DataSourceProperties dataSourceProperties() {

        return new DataSourceProperties();
    }


    @Bean(name="platformDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.platform")
    public DataSource datasource(@Qualifier("platformProperties") DataSourceProperties properties){

        return properties.initializeDataSourceBuilder().build();
    }


    @Bean(name="platformEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder,
             @Qualifier("platformDatasource") DataSource dataSource){

        return builder.dataSource(dataSource)
                .packages("com.example.clickhouse.entitys.platform")
                .persistenceUnit("platform")
                .properties(jpaProperties())
                .build();
    }


    @Bean(name = "platformTransactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(
            @Qualifier("platformEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }


    @Bean(name = "platformSqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("platformDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
//        factoryBean.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources("classpath:mappers/auth/*.xml")
//        );
        return factoryBean.getObject();
    }


    @Bean(name = "platformSqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate(@Qualifier("platformSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        props.put("hibernate.implicit_naming_strategy", "org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); // hoặc ClickHouse nếu cần
        return props;
    }

}
