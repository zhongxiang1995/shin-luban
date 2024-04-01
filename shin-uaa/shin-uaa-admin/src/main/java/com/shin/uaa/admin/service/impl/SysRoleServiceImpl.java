package com.shin.uaa.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shin.uaa.admin.mapper.SysRoleMapper;
import com.shin.uaa.admin.service.SysRoleService;
import com.shin.uaa.common.entity.SysRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shin
 * @Date 2024/4/1 13:59
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    /**
     * 根据用户ID查询角色ID列表
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<String> findRoleIdsByUserId(String userId) {
        return baseMapper.listRoleIdsByUserId(userId);
    }
}
