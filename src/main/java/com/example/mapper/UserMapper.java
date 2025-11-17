package com.example.mapper;

import com.example.pojo.User;

import java.util.List;

public interface UserMapper {

    void insert(User user);

    List<User> queryUserList(User user);
}
