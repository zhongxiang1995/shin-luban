package com.shin.uaa.admin.strategy.login;

import com.shin.common.security.config.LoginTypeProperties;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author shin
 * @Date 2024/3/31 10:37
 */
@Component
public class LoginStrategyFactory implements ApplicationContextAware {
    @Resource
    private LoginTypeProperties loginTypeProperties;

    public static Map<String,LoginStrategy> loginStrategyMap = new ConcurrentHashMap<>(16);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        loginTypeProperties.getTypes().forEach((k,v)-> loginStrategyMap.put(k,(LoginStrategy)applicationContext.getBean(v)));
    }

    public LoginStrategy getLoginStrategy(String type){
        return loginStrategyMap.get(type);
    }
}
