package com.example.pojo;

import lombok.*;

@Data
public class User {
    private Integer id;
    private String name;
    private String tel;
    //用户权限 0管理员 1普通用户
    private String permission;
    private String username;
    private String password;
    private String headUrl;
    private String token;
    private String captchaId;
    private String captchaCode;
}
