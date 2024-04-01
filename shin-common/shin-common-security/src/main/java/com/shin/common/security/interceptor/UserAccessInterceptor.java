package com.shin.common.security.interceptor;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.shin.common.core.constant.CacheConstants;
import com.shin.common.core.constant.CommonConstants;
import com.shin.common.core.util.Result;
import com.shin.common.security.annotation.ApiLogin;
import com.shin.uaa.common.dto.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author shin
 * @Date 2024/4/1 14:15
 */
@AllArgsConstructor
public class UserAccessInterceptor implements AsyncHandlerInterceptor {
    private final RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method;
        if (handler instanceof HandlerMethod) {
            method = (HandlerMethod) handler;
        } else {
            return true;
        }
        //判断访问的control是否添加ApiLogin注解
        ApiLogin apiLogin = method.getMethodAnnotation(ApiLogin.class);
        //此接口必须登录才能访问
        if (apiLogin != null) {
            //接口权限
            String permission = apiLogin.permission();
            //获取header中的ThirdSession
            String accessTokenHeard = request.getHeader(CommonConstants.HEADER_ACCESS_TOKEN);
            if(StrUtil.isNotBlank(accessTokenHeard)){
                //获取缓存中的ThirdSession
                String key = CacheConstants.USER_CACHE + accessTokenHeard;
                Object userInfoObj = redisTemplate.opsForValue().get(key);
                if(userInfoObj == null) {//token过期
                    Result r = Result.failed("token已过期");
                    this.writerPrint(response, r);
                    return Boolean.FALSE;
                }else{
                    String userInfoJsonStr = String.valueOf(userInfoObj);
                    UserInfo userInfo = JSONUtil.toBean(userInfoJsonStr, UserInfo.class);
                    return hasAuthority(userInfo,permission);
                }
            }else{
                Result r = Result.failed("token不能为空");
                this.writerPrint(response, r);
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private void writerPrint(HttpServletResponse response, Result r) throws IOException {
        //返回超时错误码，触发小程序重新登录
        response.setCharacterEncoding(CommonConstants.UTF8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        writer.print(JSONUtil.parseObj(r));
        if(writer != null){
            writer.close();
        }
    }

    private boolean hasAuthority(UserInfo userInfo,String permission){
        if (StrUtil.isEmpty(permission)) {
            return true;
        }
        return ArrayUtil.contains(userInfo.getPermissions(),permission);
    }
}
