package com.shim.gateway.entity;

import lombok.Data;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.io.Serializable;
import java.util.List;

/**
 * @Author shin
 * @Date 2024/3/31 9:03
 */
@Data
public class RouteList implements Serializable {
    private static final long serialVersionUID = -8477336477428067971L;

    List<RouteDefinition> routes;
}
