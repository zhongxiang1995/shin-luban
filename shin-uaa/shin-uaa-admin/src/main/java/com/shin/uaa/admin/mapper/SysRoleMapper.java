package com.shin.uaa.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shin.uaa.common.entity.SysRole;

import java.util.List;

/**
 * @Author shin
 * @Date 2024/4/1 14:00
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<String> listRoleIdsByUserId(String userId);
}
