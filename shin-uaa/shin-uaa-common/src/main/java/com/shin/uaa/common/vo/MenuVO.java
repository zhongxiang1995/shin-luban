package com.shin.uaa.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author shin
 * @Date 2024/4/1 14:07
 */
@Data
public class MenuVO implements Serializable {
    private static final long serialVersionUID = 887476560589529936L;

    /**
     * 菜单ID
     */
    private String id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单权限标识
     */
    private String permission;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * menuId 相同则相同
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MenuVO) {
            String targetMenuId = ((MenuVO) obj).getId();
            return id.equals(targetMenuId);
        }
        return super.equals(obj);
    }
}
