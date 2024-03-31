package com.shin.uaa.admin.strategy.login;


import com.shin.uaa.common.vo.LoginResultVo;

/**
 * 登录策略父类接口
 * @Author shin
 * @Date 2024/3/31 10:30
 */
public interface LoginStrategy {

    LoginResultVo login(String userName, String password);
}
