package com.example.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "application-userinfo.yml")
public class UserInfo {
    private Long account;//账号
    private String username;//姓名
    private Integer age;//年龄
    private Boolean sex;//性别

    private Map<Long,String> accountAndPaswd;
    private List<Object> list;

    public UserInfo(){

    }

    public UserInfo(Long account, String username, Integer age, Boolean sex, Map<Long, String> accountAndPaswd, List<Object> list) {
        this.account = account;
        this.username = username;
        this.age = age;
        this.sex = sex;
        this.accountAndPaswd = accountAndPaswd;
        this.list = list;
    }

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
