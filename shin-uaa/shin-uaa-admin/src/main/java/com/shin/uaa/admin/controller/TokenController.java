package com.shin.uaa.admin.controller;

import com.shin.common.core.util.Result;
import com.shin.common.security.annotation.ApiLogin;
import com.shin.uaa.admin.strategy.login.LoginStrategy;
import com.shin.uaa.admin.strategy.login.LoginStrategyFactory;
import com.shin.uaa.common.entity.Product;
import com.shin.uaa.common.entity.SysUser;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author shin
 * @Date 2024/3/31 10:28
 */
@RestController
@RequestMapping("/token")
@AllArgsConstructor
public class TokenController {

    private final LoginStrategyFactory loginStrategyFactory;

    @PostMapping
    public Result save(@RequestParam @Validated SysUser sysUser){
        LoginStrategy loginStrategy = loginStrategyFactory.getLoginStrategy(sysUser.getType());
        return Result.ok(loginStrategy.login(sysUser.getUsername(),sysUser.getPassword()));
    }
}
