package com.demo;

import com.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// SpringBoot 提供的用于测试的注解，使用在测试类中
@SpringBootTest
public class SpringBootDemoTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testfindAll(){
        userService.findAll();
    }
}
