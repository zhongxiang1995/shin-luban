package com.shin.uaa.common.dto;

import com.shin.uaa.common.entity.SysUser;
import lombok.Data;
import java.io.Serializable;

/**
 * @Author shin
 * @Date 2024/4/1 13:46
 */
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -3659285121359906212L;
    /**
     * 用户基本信息
     */
    private SysUser sysUser;
    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private String[] roles;
}
