package com.nju.architecture.zhuyuan.security.component;

import com.nju.architecture.zhuyuan.domain.UmsUserDetails;
import com.nju.architecture.zhuyuan.security.config.IgnoreUrlsConfig;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 动态鉴权管理器，用于判断是否有资源的访问权限
 * Created by macro on 2023/11/3.
 */
public class DynamicAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        HttpServletRequest request = requestAuthorizationContext.getRequest();
        String path = request.getRequestURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        // 白名单路径直接放行
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, path)) {
                return new AuthorizationDecision(true);
            }
        }
        // 对应跨域的预检请求直接放行
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return new AuthorizationDecision(true);
        }
        // 权限校验逻辑
        List<ConfigAttribute> configAttributeList = new ArrayList<>();
        List<String> needAuthorities = configAttributeList.stream().map(ConfigAttribute::getAttribute).collect(Collectors.toList());
        Authentication currentAuth = authentication.get();
        // 判定是否已经实现登录认证
        return new AuthorizationDecision(true);
    }
}
