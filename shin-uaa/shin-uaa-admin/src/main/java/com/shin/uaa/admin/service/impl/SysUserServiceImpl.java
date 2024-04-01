package com.shin.uaa.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shin.uaa.admin.mapper.SysUserMapper;
import com.shin.uaa.admin.service.SysMenuService;
import com.shin.uaa.admin.service.SysRoleService;
import com.shin.uaa.admin.service.SysUserService;
import com.shin.uaa.common.dto.UserInfo;
import com.shin.uaa.common.entity.SysUser;
import com.shin.uaa.common.vo.MenuVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author shin
 * @Date 2024/4/1 13:39
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysRoleService sysRoleService;
    private final SysMenuService sysMenuService;

    /**
     * 通过查用户的角色权限信息
     * @param sysUser 用户
     */
    @Override
    public UserInfo findUserInfo(SysUser sysUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);

        //设置角色列表  （ID）
        List<String> roleIds = sysRoleService.findRoleIdsByUserId(sysUser.getId());
        userInfo.setRoles(ArrayUtil.toArray(roleIds, String.class));

        //设置权限列表（menu.permission）
        Set<String> permissions = new HashSet<>();
        roleIds.forEach(roleId -> {
            List<String> permissionList = sysMenuService.findMenuByRoleId(roleId)
                    .stream()
                    .filter(menuVo -> StrUtil.isNotEmpty(menuVo.getPermission()))
                    .map(MenuVO::getPermission)
                    .collect(Collectors.toList());
            permissions.addAll(permissionList);
        });
        userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));

        return userInfo;
    }
}
