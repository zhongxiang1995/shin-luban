package com.shin.uaa.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shin.uaa.common.entity.SysRole;

import java.util.List;

/**
 * @Author shin
 * @Date 2024/4/1 13:57
 */
public interface SysRoleService extends IService<SysRole> {
    List<String> findRoleIdsByUserId(String userId);
}
