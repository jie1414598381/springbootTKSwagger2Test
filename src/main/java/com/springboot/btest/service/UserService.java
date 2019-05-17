package com.springboot.btest.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.btest.model.entity.SysUser;
import com.springboot.btest.model.vo.PageParamVo;

public interface UserService {

    JSONObject login(String username, String password);

    PageInfo<SysUser> queryUserList(PageParamVo pageParamVo);
}
