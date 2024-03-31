package com.shin.uaa.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @Author shin
 * @Date 2024/3/31 9:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends Model<SysMenu> {
    private static final long serialVersionUID = -195169997340830493L;

    /**
     * 菜单ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 菜单名称
     */
    @NotNull(message = "菜单名称不能为空")
    private String name;

    /**
     * 菜单权限标识
     */
    private String permission;
}
