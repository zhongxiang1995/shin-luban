package com.shin.uaa.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shin.uaa.common.entity.SysMenu;
import com.shin.uaa.common.vo.MenuVO;

import java.util.List;

/**
 * @Author shin
 * @Date 2024/4/1 13:59
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 通过角色编号查询菜单
     *
     * @param roleId 角色ID
     * @return
     */
    List<MenuVO> listMenusByRoleId(String roleId);
}
