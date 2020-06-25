package com.example.demo1;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//Spring Boot单元测试
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1ApplicationTests {

    @Autowired
    UserInfo user;

    @Test
    void contextLoads() {
        System.out.println(user);
    }

}
