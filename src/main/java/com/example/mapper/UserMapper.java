package com.example.mapper;

import com.example.pojo.User;

import java.util.List;

public interface UserMapper {

    void insert(User user);

    int deleteBatchIds(List<Integer> ids);

    int updateById(User user);

    List<User> queryUserList(User user);

    User queryById(Integer id);

    User queryByUsername(String username);

    int countByUsername(String username);
}
