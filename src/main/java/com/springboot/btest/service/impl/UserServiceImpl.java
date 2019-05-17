package com.springboot.btest.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.btest.common.exception.AppException;
import com.springboot.btest.dao.SysUserMapper;
import com.springboot.btest.model.entity.SysUser;
import com.springboot.btest.model.vo.PageParamVo;
import com.springboot.btest.service.RedisService;
import com.springboot.btest.service.UserService;
import com.springboot.btest.util.UUID;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.function.Predicate;

import static com.springboot.btest.common.Const.EXPIRED;
import static com.springboot.btest.common.Const.RedisKeys.YULU_SYS_USER;
import static com.springboot.btest.common.exception.ExceptionEnum.ERR_USERNAME_OR_PWD;
import static com.springboot.btest.common.exception.ExceptionEnum.USER_NOT_EXIST;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private SysUserMapper userMapper;
    private Predicate<String> notNull = StringUtils::isEmpty;
    @Override
    public JSONObject login(String username, String password) {
        if (notNull.test(username) ||notNull.test(password)){
            throw new AppException(ERR_USERNAME_OR_PWD);
        }
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser = userMapper.selectOne(sysUser);
        if (null == sysUser) {
            throw new AppException(USER_NOT_EXIST);
        }
        password = DigestUtils.md5Hex(password);
        if (!password.equals(sysUser.getPassword())) {
            throw new AppException(ERR_USERNAME_OR_PWD);
        }
        String token = UUID.gen();
        redisService.set(token, sysUser, EXPIRED);
        redisService.hset(YULU_SYS_USER, sysUser.getUid() + "", token);

        JSONObject res = new JSONObject();
        res.put("uid", sysUser.getUid());
        res.put("username", sysUser.getUsername());
        res.put("token", token);
        return res;
    }

    @Override
    public PageInfo<SysUser> queryUserList(PageParamVo pageParamVo) {
        PageHelper.startPage(pageParamVo.getStartPage(),pageParamVo.getPageSize());
        Example example = new Example(SysUser.class);
        example.orderBy("createTime").desc();
        List<SysUser> sysUsers = userMapper.selectByExample(example);
        return new PageInfo<>(sysUsers);
    }

}
