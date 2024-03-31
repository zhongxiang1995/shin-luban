package com.shin.common.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author shin
 * @Date 2024/3/31 10:41
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "login")
public class LoginTypeProperties {

    private Map<String,String> types = new HashMap<>();
}
