package com.shin.uaa.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;

/**
 * @Author shin
 * @Date 2024/3/31 9:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends Model<SysRole> {
    private static final long serialVersionUID = -4052341303217775019L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @NotNull(message = "角色名称 不能为空")
    private String roleName;

    @NotNull(message = "角色标识 不能为空")
    private String roleCode;
}
