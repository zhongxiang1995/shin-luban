package com.shin.uaa.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shin.uaa.common.entity.SysMenu;
import com.shin.uaa.common.vo.MenuVO;

import java.util.Arrays;
import java.util.List;

/**
 * @Author shin
 * @Date 2024/4/1 13:57
 */
public interface SysMenuService extends IService<SysMenu> {
    List<MenuVO> findMenuByRoleId(String roleId);
}
