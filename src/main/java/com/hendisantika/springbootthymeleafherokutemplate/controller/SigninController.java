package com.hendisantika.springbootthymeleafherokutemplate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-thymeleaf-heroku-template
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-30
 * Time: 06:20
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SigninController {
    final Logger logger = LoggerFactory.getLogger(SigninController.class);

    /**
     * Sign in page.
     */
    @GetMapping("/signin")
    public String signin() {
        logger.info("Showing sign in page");

        return "signin/signin";
    }
}