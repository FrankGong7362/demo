package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.pojo.User;
import com.example.response.R;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/insert")
    @CrossOrigin
    public R insert(User user){
        userService.insert(user);
        return R.success();
    }

    @RequestMapping("/list")
    @CrossOrigin
    public R<PageInfo<User>> queryUserList(@RequestBody User user, @RequestParam Integer pagenum, @RequestParam Integer pagesize){
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        if(ObjectUtils.isNotEmpty(user.getName())){
            userWrapper.like(User::getName,user.getName());
        }
        if(ObjectUtils.isNotEmpty(user.getTel())){
            userWrapper.like(User::getTel,user.getTel());
        }
        PageHelper.startPage(pagenum,pagesize);
        List<User> userList = userService.queryUserList(user);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return R.data(pageInfo);
    }
}
