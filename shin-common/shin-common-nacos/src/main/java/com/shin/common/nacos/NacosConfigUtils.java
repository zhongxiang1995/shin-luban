package com.shin.common.nacos;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import java.util.Properties;

/**
 * @Author shin
 * @Date 2024/3/31 8:51
 */
public class NacosConfigUtils {
    private NacosConfigProperties nacosProperties;

    /**
     * 获取ConfigService
     * @return
     * @throws NacosException
     */
    public ConfigService getConfigService() throws NacosException {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, nacosProperties.getServerAddr());
        properties.put(PropertyKeyConst.USERNAME, nacosProperties.getUsername());
        properties.put(PropertyKeyConst.PASSWORD, nacosProperties.getPassword());
        ConfigService configService = NacosFactory.createConfigService(properties);
        return configService;
    }
}
