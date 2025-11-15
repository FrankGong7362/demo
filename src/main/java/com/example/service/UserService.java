package com.example.service;

import com.example.pojo.User;

import java.util.List;

public interface UserService {
    void insert(User user);

    List<User> selectList(User user);
}
