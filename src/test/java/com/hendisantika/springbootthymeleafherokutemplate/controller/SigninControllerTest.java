package com.hendisantika.springbootthymeleafherokutemplate.controller;

import com.hendisantika.springbootthymeleafherokutemplate.SpringbootThymeleafHerokuTemplateApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-thymeleaf-heroku-template
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-31
 * Time: 05:23
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootThymeleafHerokuTemplateApplication.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class SigninControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setup() {
        SigninController signinController = new SigninController();
        this.mockMvc = MockMvcBuilders.standaloneSetup(signinController).build();
    }

    @Test
    public void testSignin() throws Exception {
        mockMvc.perform(get("/signin"))
                .andExpect(status().isOk())
                .andExpect(view().name("signin/signin"))
                .andExpect(forwardedUrl("signin/signin"));
    }
}