package com.shin.uaa.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @Author shin
 * @Date 2024/3/31 9:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends Model<SysUser> {
    private static final long serialVersionUID = 3656626697800304L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 账号类型：mysql；ldap；github
     */
    @NotNull(message = "账号类型不能为空")
    private String type;
}
