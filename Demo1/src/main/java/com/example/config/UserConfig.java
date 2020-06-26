package com.example.config;

import com.example.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Configuration指明当前类是一个配置类
 * 替代Spring配置文件
 */
@Configuration
public class UserConfig {

//    将方法的返回值添加在组件中，组件的默认ID就是方法名
    @Bean
    public UserService userService(){
        return new UserService();
    }
}
