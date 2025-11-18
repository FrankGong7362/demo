package com.example.service;

import com.example.pojo.User;

import java.util.List;

public interface UserService {

    void insert(User user);

    int deleteBatchIds(List<Integer> ids);

    int updateById(User user);

    List<User> queryUserList(User user);

    User queryByUsername(String username);

    User queryById(Integer id);

    int countByUsername(String username);
}
