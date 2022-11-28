package com.hendisantika.springbootthymeleafherokutemplate.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import uk.co.gcwilliams.jodatime.thymeleaf.JodaTimeDialect;

import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-thymeleaf-heroku-template
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-29
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */
public class LocaleConfiguration implements WebMvcConfigurer {

    /**
     * Thymeleaf LocaleResolver
     */
    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return sessionLocaleResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/i18n/messages", "classpath:/i18n/application");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Joda Time to Thymeleaf converter<br>
     * Spring Boot, in the ThymeleafAutoConfiguration class,
     * will automatically add any Beans that implement the IDialect interface.
     */
    @Bean
    public JodaTimeDialect jodaTimeDialect() {
        return new JodaTimeDialect();
    }

    /**
     * Thymeleaf - Spring Security Integration
     */
//    @Bean
//    public SpringSecurityDialect springSecurityDialect() {
//        return new SpringSecurityDialect();
//    }
}

