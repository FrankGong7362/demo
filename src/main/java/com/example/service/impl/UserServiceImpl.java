package com.example.service.impl;

import com.example.constants.PermissionConstants;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> selectList(User user) {
        List<User> userList = userMapper.selectList(user);
        //处理映射
        for (User userReq : userList){
            userReq.setPermission(PermissionConstants.getPermissionDesc(userReq.getPermission()));
        }
        return userList;
    }
}
