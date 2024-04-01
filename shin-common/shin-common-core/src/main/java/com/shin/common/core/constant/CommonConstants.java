package com.shin.common.core.constant;

/**
 * @Author shin
 * @Date 2024/3/31 8:49
 */
public interface CommonConstants {

    /**
     * 成功标记
     */
    Integer SUCCESS = 0;
    /**
     * 失败标记
     */
    Integer FAIL = 1;

    /**
     * 网关路由加载常量
     */
    String CONFIG_DATA_ID_DYNAMIC_ROUTES = "dynamic_routes";
    String CONFIG_GROUP = "DEFAULT_GROUP";
    long CONFIG_TIMEOUT_MS = 5000;

    String HEADER_ACCESS_TOKEN = "Access-Token";
    /**
     * 编码
     */
    String UTF8 = "UTF-8";
}
