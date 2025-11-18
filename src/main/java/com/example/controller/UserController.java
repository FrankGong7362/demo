package com.example.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.exception.BusinessException;
import com.example.pojo.User;
import com.example.response.R;
import com.example.response.ResponseCode;
import com.example.service.UserService;
import com.example.util.CaptchaCache;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private CaptchaCache captchaCache;

    @RequestMapping("/login")
    @CrossOrigin
    public R login(@RequestBody User user){
        if (StringUtils.isBlank(user.getCaptchaId()) || StringUtils.isBlank(user.getCaptchaCode())) {
            return R.fail(ResponseCode.CAPTCHA_ERROR);
        }
        boolean result = captchaCache.validateCaptcha(user.getCaptchaId(), user.getCaptchaCode());
        if (!result) {
            return R.fail(ResponseCode.CAPTCHA_ERROR);
        }
        captchaCache.removeCaptcha(user.getCaptchaId());
        String username = user.getUsername();
        //根据传入的用户名和密码去数据库查询
        User userInfo = userService.queryByUsername(username);
        if(userInfo == null){
            throw new BusinessException(ResponseCode.USERNAME_NOT_EXIST);
        }else if(!userInfo.getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))){
            throw new BusinessException(ResponseCode.USERNAME_PASSWORD_ERROR);
        }
        //根据ID进行登录
        StpUtil.login(user.getId());
        user.setToken(StpUtil.getTokenValue()); //设置token
        return R.data(user);
    }

    @RequestMapping("/loginOut")
    @CrossOrigin
    public R loginOut(){
        StpUtil.logout();
        return R.success();
    }

    @RequestMapping("/insert")
    @CrossOrigin
    @SaCheckLogin
    public R insert(User user){
        int count = userService.countByUsername(user.getUsername());
        if(count>0){
            throw new BusinessException(ResponseCode.USERNAME_EXIST);
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userService.insert(user);
        Integer generatedId = user.getId();
        if(generatedId>0){
            return R.success();
        }else{
            return R.fail(ResponseCode.SQL_ERROR);
        }
    }

    @RequestMapping("/delete")
    @CrossOrigin
    @SaCheckLogin
    public R delete(List<Integer> ids){
        int result = userService.deleteBatchIds(ids);
        if(result == ids.size()){
            return R.success();
        }else{
            return R.fail(ResponseCode.SQL_ERROR);
        }
    }

    @RequestMapping("/update")
    @CrossOrigin
    @SaCheckLogin
    public R update(User user){
        int result = userService.updateById(user);
        if(result == 1){
            return R.success();
        }else{
            return R.fail(ResponseCode.SQL_ERROR);
        }
    }

    @RequestMapping("/list")
    @CrossOrigin
    @SaCheckLogin
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

    /**
     * 检验用户名是否唯一
     * @param username
     * @return
     */
    @GetMapping(value = "/checkUsername")
    @CrossOrigin
    public R checkUsername(@RequestParam String username)  {
        User user = userService.queryByUsername(username);
        if (user != null && user.equals(username)) {
            return R.fail(ResponseCode.USERNAME_EXIST);
        }
        return R.success();
    }

    /**
     * 重置密码。
     * @return 返回一个表示操作结果的对象，通常表示操作成功。
     */
    @ResponseBody
    @GetMapping(value = "/resetPwd")
    @CrossOrigin
    public R resetPwd(@RequestParam Integer id)  {
        // 根据ID从数据库中获取管理员对象
        User user = userService.queryById(id);
        // 使用MD5加密新密码，并更新管理员对象的密码字段
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        // 通过ID更新管理员对象的密码信息
        userService.updateById(user);
        // 返回操作成功的响应对象
        return R.success();
    }
}
