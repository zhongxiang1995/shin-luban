package com.shin.common.security.config;

import com.shin.common.security.interceptor.UserAccessInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author shin
 * @Date 2024/4/1 14:13
 */
@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final RedisTemplate redisTemplate;

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserAccessInterceptor(redisTemplate))
                //放行接口
                .excludePathPatterns("/static/**",
                        "/token/**");//鉴权接口s
    }
}
