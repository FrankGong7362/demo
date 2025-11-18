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
    public int deleteBatchIds(List<Integer> ids) {
        return userMapper.deleteBatchIds(ids);
    }

    @Override
    public int updateById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public List<User> queryUserList(User user) {
        List<User> userList = userMapper.queryUserList(user);
        //处理映射
        for (User userReq : userList){
            userReq.setPermission(PermissionConstants.getPermissionDesc(userReq.getPermission()));
        }
        return userList;
    }

    @Override
    public User queryByUsername(String username) {
        return userMapper.queryByUsername(username);
    }

    @Override
    public User queryById(Integer id) {
        return userMapper.queryById(id);
    }

    @Override
    public int countByUsername(String username) {
        return userMapper.countByUsername(username);
    }
}
