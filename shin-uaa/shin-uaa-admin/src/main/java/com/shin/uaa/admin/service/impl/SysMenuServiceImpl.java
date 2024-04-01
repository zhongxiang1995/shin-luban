package com.shin.uaa.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shin.uaa.admin.mapper.SysMenuMapper;
import com.shin.uaa.admin.service.SysMenuService;
import com.shin.uaa.common.entity.SysMenu;
import com.shin.uaa.common.vo.MenuVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shin
 * @Date 2024/4/1 13:59
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Override
    public List<MenuVO> findMenuByRoleId(String roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }
}
