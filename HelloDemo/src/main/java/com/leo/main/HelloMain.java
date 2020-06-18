package com.leo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloMain {
//主程序，启动Spring—Boot
    public static void main(String[] args) {
        SpringApplication.run(HelloMain.class, args);
        System.out.println("Hello World");
    }

}