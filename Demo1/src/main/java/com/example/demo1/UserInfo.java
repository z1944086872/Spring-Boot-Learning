package com.example.demo1;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
/*
将配置文件中每一个属性的值映射到组件
@ConfigurationProperties告诉Spring Boot将本类中所属性和配置文件中相关的配置进行绑定
prefix = "UserInfo" ：配置文件下的属性进行识别
 */
@Component
@ConfigurationProperties(prefix = "userinfo")//默认从全局文件中获取值
public class UserInfo {
    private Long account;//账号
    private String username;//姓名
    private Integer age;//年龄
    private Boolean sex;//性别

    private Map<Long,String> accountAndPaswd;
    private List<Object> list;

    @Override
    public String toString() {
        return "UserInfo{" +
                "account=" + account +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", accountAndPaswd=" + accountAndPaswd +
                ", list=" + list +
                '}';
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Map<Long, String> getAccountAndPaswd() {
        return accountAndPaswd;
    }

    public void setAccountAndPaswd(Map<Long, String> accountAndPaswd) {
        this.accountAndPaswd = accountAndPaswd;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
