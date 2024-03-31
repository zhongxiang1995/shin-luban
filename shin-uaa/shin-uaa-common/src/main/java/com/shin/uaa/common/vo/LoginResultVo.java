package com.shin.uaa.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author shin
 * @Date 2024/3/31 10:33
 */
@Data
public class LoginResultVo implements Serializable {
    private static final long serialVersionUID = 6900005707281989491L;

    private String accessToken;
    private long durationTime;
}
