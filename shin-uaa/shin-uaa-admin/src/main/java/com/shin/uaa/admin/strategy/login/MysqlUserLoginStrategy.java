package com.shin.uaa.admin.strategy.login;

import com.shin.uaa.common.entity.SysUser;
import com.shin.uaa.common.vo.LoginResultVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author shin
 * @Date 2024/3/31 10:53
 */
@Component
public class MysqlUserLoginStrategy implements LoginStrategy{

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private SysUser sysUser;

    @Override
    public LoginResultVo login(String userName, String password) {
        return null;
    }
}
