package com.springboot.btest.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.btest.common.annotation.Log;
import com.springboot.btest.model.entity.SysUser;
import com.springboot.btest.model.vo.PageParamVo;
import com.springboot.btest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Predicate;
@Api(description = "用户相关接口")
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private UserService userService;

    @Log
    @RequestMapping("/login")
    public JSONObject login(String username,String password){
        return userService.login(username,password);
    }
    @Log
    @RequestMapping("/queryUserList")
    public PageInfo<SysUser> queryUserList(PageParamVo pageParamVo){
        return userService.queryUserList(pageParamVo);
    }

}
