package com.example;

import com.example.dto.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class Demo1ApplicationTests {

    @Autowired
    UserInfo userInfo;

    @Autowired
    ApplicationContext ioc;

    @Test
    void contextLoads() {
        System.out.println(userInfo);
    }

    @Test
    void testUserService(){
        System.out.println(ioc.isPrototype("userSerVice"));//获取容器中是否含有userSerVice
    }

}
