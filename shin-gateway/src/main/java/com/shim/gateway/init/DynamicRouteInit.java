package com.shim.gateway.init;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.shim.gateway.entity.RouteList;
import com.shin.common.core.constant.CommonConstants;
import com.shin.common.nacos.NacosConfigUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;

/**
 * @Author shin
 * @Date 2024/3/31 9:04
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class DynamicRouteInit {
    private RouteDefinitionWriter routeDefinitionWriter;
    private NacosConfigUtils nacosConfigUtils;

    @PostConstruct
    public void initRoute() {
        try {
            ConfigService configService = nacosConfigUtils.getConfigService();
            String content = configService.getConfig(CommonConstants.CONFIG_DATA_ID_DYNAMIC_ROUTES, CommonConstants.CONFIG_GROUP, CommonConstants.CONFIG_TIMEOUT_MS);
            log.info("初始化网关路由开始");
            updateRoute(content);
            log.info("初始化网关路由完成");
            //开户监听，实现动态
            configService.addListener(CommonConstants.CONFIG_DATA_ID_DYNAMIC_ROUTES, CommonConstants.CONFIG_GROUP, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("更新网关路由开始");
                    updateRoute(configInfo);
                    log.info("更新网关路由完成");
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            log.error("加载路由出错：{}", e.getErrMsg());
        }
    }

    public void updateRoute(String content){
        Yaml yaml = new Yaml();
        RouteList gatewayRouteList = yaml.loadAs(content,RouteList.class);
        gatewayRouteList.getRoutes().forEach(route -> {
            log.info("加载路由：{},{}", route.getId(), route);
            routeDefinitionWriter.save(Mono.just(route)).subscribe();
        });
    }
}
