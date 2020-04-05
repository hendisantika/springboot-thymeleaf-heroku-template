package com.hendisantika.springbootthymeleafherokutemplate.config;

import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-thymeleaf-heroku-template
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-29
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableJpaRepositories("com.hendisantika.springbootthymeleafherokutemplate")
@EnableTransactionManagement
public class DatabaseConfiguration {
    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Autowired
    private Environment env;

    @Bean
    @Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
    public DataSource dataSource() {
        log.debug("Configuring Datasource");
        if (env.getProperty("url") == null && env.getProperty("databaseName") == null) {
            log.error("Your database connection pool configuration is incorrect! The application" +
                            "cannot start. Please check your Spring profile, current profiles are: {}",
                    Arrays.toString(env.getActiveProfiles()));

            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(env.getProperty("dataSourceClassName"));
        if (env.getProperty("url") == null || "".equals(env.getProperty("url"))) {
            config.addDataSourceProperty("databaseName", env.getProperty("databaseName"));
            config.addDataSourceProperty("serverName", env.getProperty("serverName"));
        } else {
            config.addDataSourceProperty("url", env.getProperty("url"));
        }
        config.addDataSourceProperty("user", env.getProperty("username"));
        config.addDataSourceProperty("password", env.getProperty("password"));

        return new HikariDataSource(config);
    }

    /* Liquibase */
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
        liquibase.setContexts("development, production");
        liquibase.setShouldRun(true);

        log.debug("Configuring Liquibase");
        return liquibase;
    }

    @Bean
    public Hibernate4Module hibernate4Module() {
        return new Hibernate4Module();
    }

}
