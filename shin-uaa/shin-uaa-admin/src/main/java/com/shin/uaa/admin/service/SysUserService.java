package com.shin.uaa.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shin.uaa.common.dto.UserInfo;
import com.shin.uaa.common.entity.SysUser;

/**
 * @Author shin
 * @Date 2024/3/31 10:55
 */
public interface SysUserService extends IService<SysUser> {
    UserInfo findUserInfo(SysUser sysUser);
}
