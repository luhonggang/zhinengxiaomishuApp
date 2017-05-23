package com.shanlin.intelligent.controller;

import com.shanlin.intelligent.model.UserEntity;
import com.shanlin.intelligent.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shanlin.intelligent.response.BaseResponse;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类注释
 *
 * @author: hz
 * @date: 2017/4/27
 * @time: 11:31
 * @see: 链接到其他资源
 * @since: 1.0
 */
//测试1

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private LoginService loginService;


    private Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/getUser")
    public BaseResponse getUser(){
        /*UserEntity user = new UserEntity();
        user.setId(Long.valueOf("123"));
        user.setUserName("王五");*/
        UserEntity user = loginService.selectByPrimaryKey(Long.valueOf("1"));
        return new BaseResponse<>(true, "1", "成功", user);
    }

    @GetMapping("/getUserInfo")
    public BaseResponse getUserInfo(){
        /*UserEntity user = new UserEntity();
        user.setId(Long.valueOf("123"));
        user.setUserName("王五");*/
        UserEntity user = loginService.selectByPrimaryKey(Long.valueOf("1"));
        return new BaseResponse<>(true, "1", "成功", user);
    }
}
