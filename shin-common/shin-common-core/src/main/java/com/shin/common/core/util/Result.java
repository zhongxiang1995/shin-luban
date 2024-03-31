package com.shin.common.core.util;

import com.shin.common.core.constant.CommonConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用响应
 * @Author shin
 * @Date 2024/3/31 8:44
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -1517745800294444341L;

    /**
     * 返回标记：成功=0，失败=1
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public boolean isOk() {
        return code == 0;
    }

    public static <T> Result<T> ok(T data) {
        return restResult(data, CommonConstants.SUCCESS, null);
    }

    public static <T> Result<T> failed(String msg) {
        return restResult(null, CommonConstants.FAIL, msg);
    }

    private static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
