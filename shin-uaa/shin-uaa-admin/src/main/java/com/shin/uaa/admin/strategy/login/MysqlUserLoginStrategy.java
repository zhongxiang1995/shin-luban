package com.shin.uaa.admin.strategy.login;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shin.common.core.constant.CacheConstants;
import com.shin.uaa.admin.service.SysUserService;
import com.shin.uaa.common.dto.UserInfo;
import com.shin.uaa.common.entity.SysUser;
import com.shin.uaa.common.vo.LoginResultVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author shin
 * @Date 2024/3/31 10:53
 */
@Component
public class MysqlUserLoginStrategy implements LoginStrategy{

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private SysUserService sysUserService;

    @Override
    public LoginResultVo login(String userName, String password) {

        SysUser sysUser = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, userName));
        Assert.isNull(sysUser,String.format("用户信息为空 %s", userName));
        Assert.isTrue(StrUtil.equals(password,sysUser.getPassword()),"密码错误");

        LoginResultVo loginResultVo = new LoginResultVo();

        String accessToken = UUID.fastUUID().toString(true);
        loginResultVo.setAccessToken(accessToken);
        loginResultVo.setDurationTime(TimeUnit.HOURS.toSeconds(CacheConstants.USER_CACHE_DURATION));
        UserInfo userInfo = sysUserService.findUserInfo(sysUser);
        redisTemplate.opsForValue().set(CacheConstants.USER_CACHE + accessToken, JSONUtil.toJsonStr(userInfo)
                ,CacheConstants.USER_CACHE_DURATION,TimeUnit.HOURS);
        return loginResultVo;
    }
}
